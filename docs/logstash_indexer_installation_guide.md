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


then  copy script file for each  script format :
=======
then  copy script file for each  log format  :

- [indexer kiosk script](../conf/kiosk-indexer.conf)
- [indexer prepaid script](../conf/prepaid-indexer.conf)  
- [make new log format](./log_format_add_new_format.md)

copy to this folder go out then start it
```
    $cd ..
    $bin/logstash -f conf/indexer.conf &
```
for running two  script  just run it again with new  script
```
    $bin/logstash -f conf/indexer-2.conf &
```
if you want to make it run at start up go to this [doc](./logstash_make_it_service.md)
that all **done** !
