# Validating Your Setup

This lab is to ensure that all of the tools that we'll be using during this week's labs are correctly set up on your machine.

## Pre-Requisites

You should already have the following tools installed on your machine from the "self-paced learning" pre-course work.
If you don't have them installed, follow the links next to each item:

* Git - [installing git](https://github.com/ted-ncg/austin-spl-june-26/blob/master/install-git.md)
* curl - [installing curl](https://github.com/ted-ncg/austin-spl-june-26/blob/master/install-curl.md)
* Java 8 - [installing Java](https://github.com/ted-ncg/austin-spl-june-26/blob/master/install-java8.md)
* Maven 3 - [installing Maven](https://github.com/ted-ncg/austin-spl-june-26/blob/master/install-maven3.md)
* IntelliJ IDEA - [installing IDEA](https://github.com/ted-ncg/austin-spl-june-26/blob/master/install-intellij.md)

## Lab Instructions

1. Open command prompt/terminal

1. Change to your projects directory

1. Clone the repository by doing:

   `git clone https://github.com/ted-ncg/canteen`

   * If you run into proxying issues, see [proxying git](#proxying-git) for more info

1. Launch IntelliJ IDEA

1. Open the pom.xml (*not* Import)

1. Find the Test class

1. Click on the green arrow next to the class name

1. The test should pass (be green)

1. Open the Maven Projects tool tab on the right side of the window

1. Find and open the `Lifecycle` section

1. Run the `test` target task
 
1. There should be no `Failures`

## Maven Proxying

If you find that you need to use a proxy to access the various dependencies, you'll need to add the following to your settings.xml file:

1. Open or create the settings.xml file found in the .m2 directory located in your home/user directory, e.g., ~/.m2/settings.xml
2. Add the following, filling in the protocol (http or https to connect to the proxy), host, port, username, and password as appropriate:

```
    <settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                          https://maven.apache.org/xsd/settings-1.0.0.xsd">

      <proxies>
        <proxy>
          <id>VisaProxy</id>
          <active>true</active>
          <protocol>http</protocol>
          <host>proxy.visa.com</host>
          <port>8080</port>
          <username>proxyusername</username>
          <password>proxypassword</password>
          <nonProxyHosts>localhost|127.0.0.1|*.visa.com</nonProxyHosts>
        </proxy>
      </proxies>

    </settings>
```

   Note: If you already had a settings.xml file, then just add the <proxies> section to it, leaving the rest of the file as-is.

## Proxying Git

If **git** can't connect (some error like `unable to access '...'` or `Couldn't resolve host '...'`), then you may need to set up a proxy like this:

```
git config --global http.proxy http://userproxy.visa.com:80
git config --global http.sslVerify false
```

```
export http_proxy=userproxy.visa.com:80
export https_proxy=userproxy.visa.com:443 

or

set HTTP_PROXY=userproxy.visa.com:80
set HTTPS_PROXY=userproxy.visa.com:443
```

Where the username, password, server, etc. are however your proxy is set up.
