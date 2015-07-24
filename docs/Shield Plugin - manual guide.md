![enter image description here](https://www.elastic.co/assets/bltd640924504e80b71/shield-triad.png)
# **Shield**
Shield is a plugin for Elasticsearch that enables you to easily secure a cluster. With Shield, you can password-protect your data as well as implement more advanced security measures such as encrypting communications, role-based access control, IP filtering, and auditing. This guide describes how to install Shield, configure the security features you need, and interact with your secured cluster

----------
###**Getting Started with Shield**

![enter image description here](https://www.elastic.co/guide/en/shield/current/images/icons/important.png)

>The Shield plugin must be installed on every node in the cluster and every node must be restarted after installation. Plan for a complete cluster restart before beginning the installation process.

- You have Java™ 7 or above installed.
- You have downloaded elasticsearch 1.5.0+ and extracted it

#####To install and run Shield:
 1. Run **bin/plugin -i** from **HOME** to install the license plugin.
> bin/plugin -i elasticsearch/license/latest

 2. Run **bin/plugin -i** to install the Shield plugin
>bin/plugin -i elasticsearch/shield/latest
 
 3. Start Elasticsearch
>bin/elasticsearch 
>    (If Elasticsearch isn't service)

[Read more](https://www.elastic.co/guide/en/shield/current/getting-started.html)

----------
###**Managing Users**
The **`esusers`** command line tool is located in **HOME/bin/shield**

#####**About** **`esusers`**
The **`esusers`** tool manipulates two files, **`users`** and **`users_roles`**, in **`HOME/config/shield/`**. These two files store all user data for the esusers realm and are read by Shield on startup.

![enter image description here](https://www.elastic.co/guide/en/shield/current/images/icons/important.png)

> These files are managed locally by the node and are **not** managed
> globally by the cluster. This means that with a typical multi-node
> cluster, the exact same changes need to be applied on each and every
> node in the cluster.
> 
> A safer approach would be to apply the change on one of the nodes and
> have the **`users`** and **`users_roles`** files distributed/copied to all other
> nodes in the cluster (either manually or using a configuration
> management system such as Puppet or Chef).


By default, Shield checks these files for changes every 5 seconds. You can change this default behavior by changing the value of the **`watcher.interval.high`** setting in the **`elasticsearch.yml`** file.
#####Adding users
> esusers useradd `<username>`

A username must be at least 1 character and no longer than 30 characters. The first character must be a letter (a-z or A-Z) or an underscore (_). Subsequent characters can be letters, underscores (_), digits (0-9), or any of the following symbols @, -, . or $

You can specify the user’s password at the command line with the -p option. When this option is absent, the esusers command prompts you for the password. Omit the -p option to keep plaintext passwords out of the terminal session’s command history.
> esusers useradd `<username>` -p `<secret>`

Passwords must be at least 6 characters long.

You can define a user’s roles with the -r parameter. This parameter accepts a comma-separated list of role names to associate with the user.

> esusers useradd `<username>` -r `<comma-separated list of role names>`

The following example adds a new user named ***jacknich*** to the **esusers** realm. The password for this user is ***theshining***, and this user is associated with the ***logstash*** and ***marvel*** roles.

> HOME/bin/shield/esusers useradd ***`jacknich`*** -p ***`theshining`*** -r ***`logstash`***, ***`marvel`***

#####Listing users and roles
The esusers list command lists the users registered in the esusers realm, as in the following example:
> ./bin/shield/esusers **`list`** 
> **`rdeniro`**        : **`admin`** 
> **`alpacino`**       : **`power_user`**
> **`jacknich`**       : **`marvel`**,**`logstash`**

Users are in the left-hand column and their corresponding roles are listed in the right-hand column.

The `esusers list <username>` command lists a specific user. Use this command to verify that a user has been successfully added to the cluster.

> ./bin/shield/esusers list jacknich 
> **`jacknich`**       : **`marvel`**,**`logstash`**

#####Managing user passwords
The `esusers passwd` command enables you to reset a user’s password. You can specify the new password directly with the `-p` option. When `-p` option is omitted, the tool will prompt you to enter and confirm a password in interactive mode.

> esusers passwd `<username>`

-p option
> esusers passwd `<username>` -p `<password>`

#####Managing users' roles

The `esusers roles` command manages the roles associated to a particular user. The `-a` option adds a comma-separated list of roles to a user. The `-r` option removes a comma-separated list of roles from a user. You can combine adding and removing roles within the same command to change a user’s roles.

The following command removes the ***`logstash`*** and ***`marvel`*** roles from user ***`jacknich`***, as well as adding the ***`user`*** role:
>./bin/shield/esusers roles ***`jacknich`*** -r ***`logstash`***,***`marvel`*** -a ***`user`***

Listing the user displays the new role assignment:
>./bin/shield/esusers list ***`jacknich`***
>***`jacknich`***       : ***`user`***

######Removing users
The **`esusers userdel`** command deletes a user.
>esusers userdel `<username>`

Example
>./bin/shield/esusers userdel **`jacknich`**

[Read more](https://www.elastic.co/guide/en/shield/current/_managing_users_in_an_esusers_realm.html)


----------
#[**Source**][Source]
[Source]:https://www.elastic.co/guide/en/shield/current/index.html
