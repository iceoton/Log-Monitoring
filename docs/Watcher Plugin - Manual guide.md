![enter image description here](https://www.elastic.co/assets/bltc662be3f9f0cdf60/graph-watcher.svg)
#**Watcher**
Watcher is a plugin for Elasticsearch that provides alerting and notification based on changes in your data. This guide describes how to install, manage, and use Watcher.


----------


###**Getting Started**
All you need to use Watcher is:

-	Java 7 or later
-	Elasticsearch 1.5 or later

#####To install and run Watcher:

 1. Run **`bin/plugin -i`** from **`HOME`** to install the License plugin:
 >bin/plugin -i elasticsearch/license/latest
 

 ![enter image description here](https://www.elastic.co/guide/en/watcher/current/images/icons/note.png)  
    
    You need to install the License and Watcher plugins on each node in
     your cluster
 2. Run **`bin/plugin -i`** to install the Watcher plugin.
 
 >bin/plugin -i elasticsearch/watcher/latest
 
 3. Start Elasticsearch
>bin/elasticsearch 
>    (If Elasticsearch isn't service)

[Read more](https://www.elastic.co/guide/en/watcher/current/getting-started.html)


----------
####Sending Email from Gmail
Use the following email account settings to send email from the **Gmail** SMTP service:
