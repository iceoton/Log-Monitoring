# logstash  forwarder installation guide
we use logstash forwarder  for forward incoming log from SQS
requirement is
- centos 7
- *java version* more than 1.7.2

first download and  extract  logstash 1.5.2  
```
    $curl -O https://download.elasticsearch.org/logstash/logstash/logstash-1.5.2.tar.gz
    $tar -zxvf logstash-1.5.2.tar.gz
```
go to logstash-1.5.2 folder make  conf folder
```
    $cd logstash-1.5.2
    $mkdir conf
```

then  copy script file for each  script format :

- [forwarder kiosk script](../conf/prepaid-log-forwarder.conf)  
- [forwarder prepaid script](../conf/kiosk-log-forworder.conf)
- [make new log format](./How to  add new log format.md)

copy to this folder go out then start it
```
    $cd ..
    $bin/logstash -f conf/forward.conf &
```
for running two  script  just run it again with new  script
```
    $bin/logstash -f conf/forwarder-2.conf &
```
or [check it out](./logstash_make_it_service.md) if you want it to make it service

that all **done** !
