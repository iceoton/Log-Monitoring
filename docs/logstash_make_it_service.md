Logstash come with command to  control and use script file.We can't manage script file by configuration file yet. So start  copy [initfile](https://github.com/fhd/init-script-template/blob/master/template)  to /etc/init.d
```
    $cp init /etc/init.d/
```
rename to your program and change  permission  
```
    $mv init logstash
```
change script to our program in line 13 to

>cmd="/data/logstash-1.5.1/bin/logstash -f /data/logstash-1.5.1/conf/prepaid_forworder.conf  &"


for more information go to [original idea](https://github.com/fhd/init-script-template)
