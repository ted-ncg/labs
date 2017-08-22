# Validating Your Setup

This lab is to ensure that all of the tools that we'll be using during this week's labs are correctly set up on your machine.

## Pre-Requisites

### Developer Setup

If you haven't already done this as part of your developer on-boarding, you'll want to look at this page for Maven/Artifactory setup as well as Git and Proxying information.

Go to: https://visawiki.trusted.visa.com/display/VDP/New+Developer+Onboarding+Checklist

#### Sections of Interest:

- Proxy Settings in Terminal
- Git Config Settings
- Maven Settings

----

You should already have the following tools installed on your machine from the "self-paced learning" pre-course work.
If you don't have them installed, follow the links next to each item:

* Git - [installing git](https://github.com/ted-ncg/austin-spl-june-26/blob/master/install-git.md)
* curl - [installing curl](https://github.com/ted-ncg/austin-spl-june-26/blob/master/install-curl.md)
* Java 8 - [installing Java](https://github.com/ted-ncg/austin-spl-june-26/blob/master/install-java8.md)
* Maven 3 - [installing Maven](https://github.com/ted-ncg/austin-spl-june-26/blob/master/install-maven3.md)
* IntelliJ IDEA - [installing IDEA](https://github.com/ted-ncg/austin-spl-june-26/blob/master/install-intellij.md) -- **NOTE** install the *Ultimate* Edition for the 30-day trial as it has Spring-specific tools.

----

# Lab Instructions

1. Open command prompt/terminal

1. Change to your projects directory

1. Clone the project repository to your machine by doing:

   `git clone https://github.com/ted-ncg/foster-city-canteen`

   * If you run into proxying issues, see [proxying git](#proxying-git) for more info

1. Launch IntelliJ IDEA

1. Do `File` > `Open...` from the menus to open the pom.xml (*don't* use Import) in the `foster-city-canteen` project directory.

1. Find the `CanteenApplicationTest` class -- this is the *test* class, not the application itself.

1. Click on the green arrow next to the class name

1. The test should pass (be green)

1. Open the `Maven Projects` tool tab on the right side of the window

1. Find and open the `Lifecycle` section

1. Run the `test` target task
 
1. There should be no `Failures`

## Add Your Name

1. Create a new branch with **your name** and make it the current branch (`checkout`).
    You can do this from the command-line by typing (replace `yourname` with, well, *your* name):
    
    `git checkout -b yourname`

    You should see:
     
    `Switched to a new branch 'yourname'`
    
1. Open up the `README.md` file inside of IDEA.

1. Underneath where it says `## Participants`, add your name.

1. From with IDEA (or via the command-line):

   * Add the file to Git (`git add README.md`)
   * Commit the file (`git commit -m 'Added Ted Young to participants.'`)
   * Push the file up to GitHub (`git push --set-upstream origin <branch name here>`) -- remember to use the branch name that you created in the first step.

**NOTE:** You will likely run into a permissions issue. THAT IS OK! Call the instructor over when you run into it.


----

**NOTE:** Only follow these directions if the developer onboarding page didn't work for you.

### Maven Proxying

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
          <id>VisaHttpProxy</id>
          <active>true</active>
          <protocol>http</protocol>
          <host>proxy.visa.com</host>
          <port>80</port>
          <nonProxyHosts>localhost|127.0.0.1|*.visa.com</nonProxyHosts>
        </proxy>
        <proxy>
          <id>VisaHttpsProxy</id>
          <active>true</active>
          <protocol>https</protocol>
          <host>proxy.visa.com</host>
          <port>443</port>
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
