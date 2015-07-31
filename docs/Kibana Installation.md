#Kibana Installation

##Installation steps
1. Download  ไฟล์สำหรับติดตั้งไปไว้ที่ที่ต้องการจะติดตั้ง

	```
	$ wget https://download.elastic.co/kibana/kibana/kibana-4.1.1-linux-x64.tar.gz
		
	```
	
2. แตกไฟล์โดยใช้คำสั่ง 

	```
	$ tar-zxvf  kibana-4.1.1-linux-x64.tar.gz 
	```
	
3. แก้ไขไฟล์ kibana.ymlใน   kibana-4.1.1-linux-x64/config
		-	elasticsearch_url: {IP ของเครื่อง elasticsearch : port}
		-	ถ้า elasticsearch มี shield ให้แก้ไข 	kibana_elasticsearch_username: {username}
									kibana_elasticsearch_password: {password}
									
4. ทำให้ kibana เป็น service โดยใช้คำสั่งสร้างไฟล์ vi /etc/systemd/system/kibana.service

	```
	[Service]
	ExecStart={directory ของ kibana /kibana-4.1.1-linux-x64/bin/kibana}  
	Restart=always
	StandardOutput=syslog
	StandardError=syslog
	SyslogIdentifier=kibana
	User=root
	Group=root
	Environment=NODE_ENV=production
	
	[Install]
	WantedBy=multi-user.target
	```
	
	
5.  เริ่มการทำงานของ Kibana โดยใช้คำสั่ง

	```
	$ systemctl start kibana.service
	$ systemctl enable kibana.service
	```

6. สามารถใช้งาน kibana ได้ผ่านทาง {ip-address:5601}
