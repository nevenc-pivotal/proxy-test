# Configure Proxy Server (Squid) on Linux Ubuntu

Squid is a proxy (cache) application with various use cases. We are using Squid as a HTTP proxy server.

## Install Squid

* Install necessary packages

```
sudo apt-get update
sudo apt-get upgrade
sudo apt-get install squid
```

* Copy template configuration for future reference, e.g.

```
sudo cp /etc/squid3/squid.conf /etc/squid3/squid.conf.default
```

* Remove comments and empty lines, e.g.

```
grep -v '^#' /etc/squid3/squid.conf.default | grep -v '^$' > /etc/squid3/squid.conf.clean
```

* Your clean Squid configuration will look somewhat similar to:

```
acl SSL_ports port 443
acl Safe_ports port 80		# http
acl Safe_ports port 21		# ftp
acl Safe_ports port 443		# https
acl Safe_ports port 70		# gopher
acl Safe_ports port 210		# wais
acl Safe_ports port 1025-65535	# unregistered ports
acl Safe_ports port 280		# http-mgmt
acl Safe_ports port 488		# gss-http
acl Safe_ports port 591		# filemaker
acl Safe_ports port 777		# multiling http
acl CONNECT method CONNECT
http_access deny !Safe_ports
http_access deny CONNECT !SSL_ports
http_access allow localhost manager
http_access deny manager
http_access allow localhost
http_access deny all
http_port 3128
coredump_dir /var/spool/squid3
refresh_pattern ^ftp:		1440	20%	10080
refresh_pattern ^gopher:	1440	0%	1440
refresh_pattern -i (/cgi-bin/|\?) 0	0%	0
refresh_pattern (Release|Packages(.gz)*)$      0       20%     2880
refresh_pattern .		0	20%	4320
```

* Once you are happy with the configuration, update the main configuration file with your clean configuration, e.g.

```
sudo cp /etc/squid3/squid.conf.clean /etc/squid3/squid.conf
```

* Restart Squid process, e.g.

```
sudo service squid3 stop
sudo service squid3 start
```

* You can troubleshoot problems in the log files, e.g.

```
sudo tail -f /var/log/squid3/access.log
(CTRL+C to stop)
```

* More information and errors can be found in Syslog, e.g.

```
sudo tail -f /var/log/syslog
(CTRL+C to stop)
```

## Configure Squid




