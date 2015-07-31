#Puppet agent configuration

1. ปิด firewall

	* 1.1 selinux
		
			```	
			$cd /etc/sysconfig
			$vi selinux
			```
			
		จากนั้นแก้ SELINUX=enforcing เป็น disabled
		แล้ว `Save and Exit`
		
	* 1.2 iptables
			
			$service iptables stop
			
2. แก้ไข hostname

	```
	$vi /etc/hosts
	```
	
	เพิ่ม {ip address ของ master} {ชื่อ hostname ของ master}
	
	
3. restart network
	
	```
	$/etc/init.d/network restart
	```
	
4. ที่ server master กำหนดให้รับคำขอทุกตัว
	* เครื่อง server master สร้างไฟล์ขึ้นมา
	
		```
		$vi /etc/puppetlabs/puppet/autosign.conf
		```
		
		`ใส่ * ลงในไฟล์`
	
5. ใช้คำสั่งเพื่อเรียกขอตัว master

	```
	$ curl -k https://puppet.example.local:8140/packages/current/install.bash | sudo bash
	```