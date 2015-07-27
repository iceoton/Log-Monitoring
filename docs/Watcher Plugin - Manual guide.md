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
 
 ![note](https://www.elastic.co/guide/en/watcher/current/images/icons/note.png)  
    
    You need to install the License and Watcher plugins on each node in
     your cluster
 2. Run **`bin/plugin -i`** to install the Watcher plugin.
 
 >bin/plugin -i elasticsearch/watcher/latest
 
 3. Start Elasticsearch
>bin/elasticsearch 
>    (If Elasticsearch isn't service)

[Read more](https://www.elastic.co/guide/en/watcher/current/getting-started.html)


----------
###Configuring Watcher to Send Email
####Sending Email from Gmail
Use the following email account settings to send email from the [**Gmail**](https://mail.google.com/mail/u/0/#inbox) SMTP service:
```watcher.actions.email.service.account:  
     gmail_account: 
        profile: gmail 
        smtp:
            auth: true
            starttls.enable: true
            host: smtp.gmail.com
            port: 587
            user: <username>
            password: <password>
 ```

If you get an authentication error that indicates that you need to continue the sign-in process from a web browser when Watcher attempts to send email, you need to configure Gmail to [Allow Less Secure Apps to access your account](https://support.google.com/accounts/answer/6010255?hl=en).

If two-step verification is enabled for your account, you must generate and use a unique App Password to send email from Watcher.[See Sign in using App Passwords](https://support.google.com/accounts/answer/185833?hl=en) for more information.

####Sending Email from Outlook
Use the following email account settings to send email action from the [Outlook.com](https://www.outlook.com/) SMTP service:

```watcher.actions.email.service.account:
    outlook_account:
        profile: outlook
        smtp:
            auth: true
            starttls.enable: true
            host: smtp-mail.outlook.com
            port: 587
            user: <username>
            password: <password>
```
 ![Note](https://www.elastic.co/guide/en/watcher/current/images/icons/note.png)
 
 You need to use a unique App Password if two-step verification is enabled. See [App passwords and two-step verification](http://windows.microsoft.com/en-us/windows/app-passwords-two-step-verification) for more information. 

####Sending Email from Exchange
Use the following email account settings to send email action from Microsoft Exchange:

```watcher.actions.email.service.account:
    exchange_account:
        profile: outlook
        email_defaults:
          from: <email address of service account>  ----1
        smtp:
            auth: true
            starttls.enable: true
            host: <your exchange server>
            port: 587
            user: <email address of service account>  ----2
            password: <password>
```
![1](https://www.elastic.co/guide/en/watcher/current/images/icons/callouts/1.png)  Some organizations configure Exchange to validate that the from field is a valid local email account.

----------
![2](https://www.elastic.co/guide/en/watcher/current/images/icons/callouts/2.png) 
Many organizations support use of your email address as your username, though it is a good idea to check with your system administrator if you receive authentication-related failures.

####Sending Email from Amazon SES
Use the following email account settings to send email from the [Amazon Simple Email Service](http://aws.amazon.com/ses) (SES) SMTP service:

```watcher.actions.email.service.account:
    ses_account:
        smtp:
            auth: true
            starttls.enable: true
            starttls.required: true
            host: email-smtp.us-east-1.amazonaws.com ----1
            port: 587
            user: <username>
            password: <password>
```
![1](https://www.elastic.co/guide/en/watcher/current/images/icons/callouts/1.png) **`smtp.host`** varies depending on the region

![Note](https://www.elastic.co/guide/en/watcher/current/images/icons/note.png)

You need to use your Amazon SES SMTP credentials to send email through Amazon SES. For more information, see [Obtaining Your Amazon SES SMTP Credentials](http://docs.aws.amazon.com/ses/latest/DeveloperGuide/smtp-credentials.html).


----------


Example: Sending Email from Gmail

``` watcher.actions.email.service.account:
    gmail_account:
        profile: gmail
        smtp:
            auth: true
            starttls.enable: true
            host: smtp.gmail.com
            port: 587
            user: scouter.ascend@gmail.com
            password: sprintfail
```


**Table  Email Account Attributes**  


Name  	      |  Required     | Default	     |  Description  
-----------  |  -----------  |  ----------- |  ----------- 
profile | 	 no		    | standard | The[profile](https://www.elastic.co/guide/en/watcher/current/email-services.html#email-profile) to use to build the MIME messages that are sent from the account. Valid values: **`standard`** (default), **`gmail`** and **`outlook`**.  email_defaults.* | no | - |  An optional set of email attributes to use as defaults for the emails sent from the account. See [Email Action Attributes](https://www.elastic.co/guide/en/watcher/current/actions.html#email-action-attributes) for the supported attributes. for the possible email attributes)
smtp.auth|no|false|When **`true`**, attempt to authenticate the user using the AUTH command.
smtp.host|yes|-|The SMTP server to connect to.
smtp.port|no|25|The SMTP server port to connect to.
smtp.user|yes|-|The user name for SMTP.
smtp.password|no|-|The password for the specified SMTP user.
smtp.starttls.enable|no|false|When **`true`**, enables the use of the **`STARTTLS`** command (if supported by the server) to switch the connection to a TLS-protected connection before issuing any login commands. Note that an appropriate trust store must configured so that the client will trust the server’s certificate. Defaults to **`false`**.
smtp.*|no|-|SMTP attributes that enable fine control over the SMTP protocol when sending messages. See [com.sun.mail.smtp](https://javamail.java.net/nonav/docs/api/com/sun/mail/smtp/package-summary.html) for the full list of SMTP properties you can set.

[Read More](https://www.elastic.co/guide/en/watcher/current/email-services.html)

------------------
### Trigger, Condution, Action  for sending Email  
In  ‘…...’ is JSON
####Example

```
curl -u watcher:watcher -XPUT 'http://localhost:9200/_watcher/watch/kiosk_timeout' -d '{---1
  "trigger" :{ "schedule" : {"hourly" : { "minute" : 0 } }},   ---2
  "input" : {
    "search" : {
      "request" : {
        "indices" : [ "kiosk-*" ], ---3
        "body" : {
          "query" : {
           "match" : { "message": "java.net.SocketTimeoutException" } ---4
          },
          "filter" : {
              "range" : {"@timestamp" : {"gte" : "now-1h"}} ---5
          }
        }
      }
    }
  },
  "condition" : {
    "compare" : { 
      "ctx.payload.hits.total" : { "gte" : 10 } ---6
      }
   },
  "actions" : {
    "email_timeout_kiosk" : { ---7
      "email" : {
        "to" : "scouter.ascend@gmail.com", ---8
        "subject" : "you have {{ctx.payload.hits.total}} java.net.SocketTimeoutException ", ---9
        "body" : "pleace, check your log", ---10
          "attach_data" : true, ---11
          "priority" : "high" ---12
      }
    }
  }
}'
```


1)  `-u watcher:watcher -XPUT 'http://localhost:9200/_watcher/watch/kiosk_timeout' -d '{.....}’`

> -u watcher:watcher is the username:password for Watcher  
> -XPUT is Input command  
> kiosk_timeout is watcher's name of action  
> -d'{.....}’  in ’{}’ is command


2)  `"trigger" :{ "schedule" : {"hourly" : { "minute" : 0 } }},`

> is Watcher  trigger schedule
> in the example, It will trigger every hour such as 9.00 , 12.00, 15.00

[Read More](https://www.elastic.co/guide/en/watcher/current/trigger.html)


3)  `"indices" : [ "kiosk-*" ],`  
> Watching at Kiosk's indices



4)  `"query" : { "match" : { "message": "java.net.SocketTimeoutException" } },`
> Query tag name "message"   
> Search the word "java.net.SocketTimeoutException"  

Read More : 
[Query](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-query-string-query.html)



5)  `"filter" : { "range" : {"@timestamp" : {"gte" : "now-1h"}}}`
> tag "@timestamp” is time compare  
> "gte" is greater than equal  
> "now-1h" is now to 1 hour ago  

Read More :
[Range Filter]( https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-range-filter.html)
And
[Filter](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-filtered-query.html)

6)  `"condition" : {"compare" : { "ctx.payload.hits.total" : { "gte" : 10 }  } },`
> "ctx.payload.hits.total" is total payload
"gte" is greater than equal

[Read More](https://www.elastic.co/guide/en/watcher/current/condition.html)


7)  `"email_timeout_kiosk"`   
> is Action's name

8) `"to" : "scouter.ascend@gmail.com",`
> is recipient Email

9)  `"subject" : "you have {{ctx.payload.hits.total}} java.net.SocketTimeoutException ",`
> is content in subject from  
> {{ctx.payload.hits.total}} is total payload at the time


10) `"body" : "pleace, check your log",` 
> is content in body from

11) `"attach_data" : true,` 
> attach the log in Email

12) `"priority" : "high"`
> is priority level of action 

[Read More](https://www.elastic.co/guide/en/watcher/current/actions.html)

------------------------
