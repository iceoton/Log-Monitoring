#Tomcat8 Installation on a CentOS 6.5
![](https://www.rosehosting.com/blog/wp-content/uploads/2014/05/how-to-install-tomcat-8-on-centos-6-vps.png)  
( Source : https://secure.rosehosting.com/blog/tag/tomcat/)

##Prerequisite
  * Linux OS 6.5
  * Oracle JDK 8

##Installation steps
1. Create a separate user which will run the Tomcat server:
	
	```
	$ useradd -r tomcat8 --shell /bin/false 
	```
	
2. Download the latest Tomcat 8 version from [here](http://tomcat.apache.org/download-80.cgi) or use the following command to download Tomcat 8.0.5

	```
	$ wget http://mirrors.koehn.com/apache/tomcat/tomcat-8/v8.0.5/bin/apache-tomcat-8.0.5.tar.gz -P /tmp 
	```
	
3. Extract the contents of the Tomcat archive to `/opt` using the following command:

	```
	$ tar -zxf /tmp/apache-tomcat-8.0.5.tar.gz -C /opt
	```
	
	```
	$ ln -s /opt/apache-tomcat-8.0.5 /opt/tomcat-latest
	$ chown -hR tomcat8: /opt/tomcat-latest /opt/apache-tomcat-8.0.5
	```
	
4. Make Toncat8 to service ,Create the following init script in `/etc/init.d/tomcat8`

	```
	#!/bin/bash
	#
	# tomcat8
	#
	# chkconfig: - 80 20
	#
	### BEGIN INIT INFO
	# Provides: tomcat8
	# Required-Start: $network $syslog
	# Required-Stop: $network $syslog
	# Default-Start:
	# Default-Stop:
	# Description: Tomcat 8
	# Short-Description: start and stop tomcat
	## END INIT INFO	
	
	## Source function library.
	#. /etc/rc.d/init.d/functions
	export JAVA_HOME=/usr/java/default
	export JAVA_OPTS="-Dfile.encoding=UTF-8 \
  	-Dnet.sf.ehcache.skipUpdateCheck=true \
  	-XX:+UseConcMarkSweepGC \
  	-XX:+CMSClassUnloadingEnabled \
  	-XX:+UseParNewGC \
  	-XX:MaxPermSize=128m \
  	-Xms512m -Xmx512m"
	export PATH=$JAVA_HOME/bin:$PATH
	TOMCAT_HOME=/opt/tomcat-latest
	TOMCAT_USER=tomcat8
	SHUTDOWN_WAIT=20
	
	tomcat_pid() {
  		echo `ps aux | grep org.apache.catalina.startup.Bootstrap | grep -v grep | awk '{ print $2 }'`
	}
	
	start() {
  		pid=$(tomcat_pid)
  		if [ -n "$pid" ] 
  		then
    		echo "Tomcat is already running (pid: $pid)"
  		else
  			# Start tomcat
    		echo "Starting tomcat"
    		ulimit -n 100000
    		umask 007
    		/bin/su -p -s /bin/sh $TOMCAT_USER $TOMCAT_HOME/bin/startup.sh
    		
  		fi
  		
  		return 0
	}
  	
  	stop() {
  		pid=$(tomcat_pid)
  		if [ -n "$pid" ]
  		then
  			echo "Stoping Tomcat"
    		/bin/su -p -s /bin/sh $TOMCAT_USER $TOMCAT_HOME/bin/shutdown.sh

    		let kwait=$SHUTDOWN_WAIT
    		count=0;
    		until [ `ps -p $pid | grep -c $pid` = '0' ] || [ $count -gt $kwait ]
    		do
      			echo -n -e "\nwaiting for processes to exit";
      			sleep 1
      			let count=$count+1;
    		done

    		if [ $count -gt $kwait ]; then
      			echo -n -e "\nkilling processes which didn't stop after
      			$SHUTDOWN_WAIT seconds"
      			kill -9 $pid
  		  	fi
  		  	
  		else
    		echo "Tomcat is not running"
  		fi
  		
  		return 0
	}
	
	case $1 in
	start)
  		start
	;; 
	stop)   
  		stop
	;; 
	restart)
  		stop
  		start
	;;
	status)
  		pid=$(tomcat_pid)
  		if [ -n "$pid" ]
  		then
    		echo "Tomcat is running with pid: $pid"
  		else
    		echo "Tomcat is not running"
  		fi
	;; 
	esac    
	exit 0

	```


	
5. make the script executable using `chmod`

	```
	$ chmod +x /etc/init.d/tomcat8
	```
	
6. Start the Tomcat 8 server using:

	```
	$ service tomcat8 start
	```
	
7. Add the Tomcat 8 service to system startup:

	```
	$ chkconfig tomcat8 on
	```
	
	Access your newly installed Tomcat at http://YOUR_IP:8080