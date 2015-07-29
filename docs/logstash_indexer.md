# logstash  indexer installation guide 
we use logstash indexer  for indexd incoming log from SQS
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

then  copy script file for each  log format  : 

- [indexer kiosk script](../conf/kiosk-indexer.conf)     
- [indexer prepaid script](../conf/prepaid-indexer.conf)  
- [make new log format](./How to  add new log format.md) 

copy to this folder go out then start it 
```
    $cd ..
    $bin/logstash -f conf/indexer.conf & 
```
for running two  script  just run it again with new  script
```
    $bin/logstash -f conf/indexer-2.conf &
```
or if you want it to be service and running at start up time copy [start up script](startup.sh) to `/etc/ini.p`
then make it a service  file 
```
    $service 
```

that all **done** ! 
