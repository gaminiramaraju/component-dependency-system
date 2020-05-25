How to run?

1) Download  project from https://git@github.com:gaminiramaraju/component-dependency-system.git

2) Get the path from target folder , 

3) in command prompt , java -jar component-dependency-system.jar com.cds.ComponentDependencySystemApplication "< fullpath to input text>/input.txt"


Context
System Dependencies
Components of computer systems often have dependencies--other components that must be installed before they will function properly. These dependencies are frequently shared by multiple components. For example, both the TELNET client program and the FTP client program require that the TCP/IP networking software be installed before they can operate. If you install TCP/IP and the TELNET client program, and later decide to add the FTP client program, you do not need to reinstall TCP/IP.
For some components it would not be a problem if the components on which they depended were reinstalled; it would just waste some resources. But for others, like TCP/IP, some component configuration may be destroyed if the component was reinstalled.
It is useful to be able to remove components that are no longer needed. When this is done, components that only support the removed component may also be removed, freeing up disk space, memory, and other resources. But a supporting component, not explicitly installed, may be removed only if all components which depend on it are also removed. For example, removing the FTP client program and TCP/IP would mean the TELNET client program, which was not removed, would no longer operate. Likewise, removing TCP/IP by itself would cause the failure of both the TELNET and the FTP client programs. Also, if we installed TCP/IP to support our own development, then installed the TELNET client (which depends on TCP/IP) and then still later removed the TELNET client, we would not want TCP/IP to be removed.
Problem Statement
We want a program to automate the process of adding and removing components. To do this we will maintain a record of installed components and component dependencies. A component can be installed explicitly in response to a command (unless it is already installed), or implicitly if it is needed for some other component being installed. Likewise, a component, not explicitly installed, can be explicitly removed in response to a command (if it is not needed to support other components) or implicitly removed if it is no longer needed to support another component.
Input
The input will contain a sequence of commands (as described below), each on a separate line containing no more than eighty characters. Item names are case sensitive, and each is no longer than ten characters. The command names (DEPEND, INSTALL, REMOVE and LIST) always appear in uppercase starting in column one, and item names are separated from the command name and each other by one or more spaces. All appropriate DEPEND commands

will appear before the occurrence of any INSTALL command that uses them. There will be no circular dependencies. The end of the input is marked by a line containing only the word END.
  Command
DEPEND item1 item2 [item3 ...] INSTALL item1
REMOVE item1
LIST
Output
Interpretation
item1 depends on item2 (and item3 ...)
install item1 and those on which it depends remove item1, and those on which it depends, if possible.
list the names of all currently installed components.
          Echo each line of input. Follow each echoed INSTALL or REMOVE line with the actions taken in response, making certain that the actions are given in the proper order. Also identify exceptional conditions (see Sample Output, below, for examples of all cases). For the LIST command, display the names of the currently installed components in the installation order. No output, except the echo, is produced for a DEPEND command or the line containing END. There will be at most one dependency list per item.
Sample Input
DEPEND TELNET TCPIP NETCARD DEPEND TCPIP NETCARD
DEPEND DNS TCPIP NETCARD DEPEND BROWSER TCPIP HTML INSTALL NETCARD
INSTALL TELNET
INSTALL foo
REMOVE NETCARD
INSTALL BROWSER
INSTALL DNS
LIST
REMOVE TELNET
REMOVE NETCARD
REMOVE DNS
REMOVE NETCARD
INSTALL NETCARD
REMOVE TCPIP
REMOVE BROWSER
REMOVE TCPIP
END
Sample Output
DEPEND TELNET TCPIP NETCARD DEPEND TCPIP NETCARD
DEPEND DNS TCPIP NETCARD

DEPEND BROWSER TCPIP HTML INSTALL NETCARD
   Installing NETCARD
INSTALL TELNET
   Installing TCPIP
   Installing TELNET
INSTALL foo
   Installing foo
REMOVE NETCARD
NETCARD is still needed. INSTALL BROWSER
   Installing HTML
   Installing BROWSER
INSTALL DNS
   Installing DNS
LIST
   NETCARD
   TCPIP
   TELNET
   foo
   HTML
   BROWSER
   DNS
REMOVE TELNET
   Removing TELNET
REMOVE NETCARD
NETCARD is still needed.
REMOVE DNS
   Removing DNS
REMOVE NETCARD
NETCARD is still needed.
INSTALL NETCARD
NETCARD is already installed.
REMOVE TCPIP
TCPIP is still needed.
REMOVE BROWSER
   Removing BROWSER
   Removing HTML
   Removing TCPIP
REMOVE TCPIP
TCPIP is not installed.
END
Implicit Requirements
1. Solution must have a robust design.
2. Solution must be extensible i.e. we may need to add a new command later.
3. Unit tests should be present.
How to share your solution with us?
1. Create a public github repository.
2. Commit your code there with a README file describing how to run the application.
3. Share the repository url with us.
