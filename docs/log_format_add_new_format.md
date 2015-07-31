#how to add new  log type
-  [make new queue](How to use amazon SQS.md) for your service
-  [add logstash forwarder](./logstash_forwarder_installation_guide.md) and [change queue name](#change-queue-name) to new queue
-  [add indexer ](./logstash_indexer_installation_guide.md)  with [new grok](https://grokdebug.herokuapp.com/) or [ruby capture script](https://www.elastic.co/guide/en/logstash/current/plugins-filters-ruby.html)
-  make [kibana  dashboard](./kibana_installation.md)
###change queue name
change queue name on forwarder and indexer by go to script file on output section
```ruby
sqs {
   queue => "Your_queue_name"
  }
```
