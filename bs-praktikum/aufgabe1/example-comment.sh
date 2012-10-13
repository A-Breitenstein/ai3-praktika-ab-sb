#!/bin/bash 
# <Description of this shell script>
# <Breitenstein, Bartel> 
# <10.10.2012>

# ------------------------------------------------------------
# This function shows the help text for this bash script
usage() { 
   echo "
	$0 [OPTIONS] [<user name>]
	Ask the user for her or his name and display a greeting 
	OPTIONS: 
	   -h: Display this help
	"
}

# This function asks the user for his name
ask_for_name() {
    echo "Please enter your name:" 
    read user_name          #lese input
}
#Kommentar zu: example::ask_for_name():
: '
read - Read a line from standard input
'

# ---------------------- main --------------------------------
# check parameters 
if [ $# -gt 1 ]; then   #Anzahl aller Parameter größer 1
    usage               #function call
    exit 1              #exit aufgrund von general error
fi
#Kommentar zu: 'check parameters' Abfrage:
: '
$0 is the name of the command
$1 first parameter
$2 second parameter
$3 third parameter etc. etc
$# total number of parameters
$@ all the parameters will be listed
'
#Kommentar zu: expression: [$# -gt 1]:
: '
-eq is equal to 
-ne is not equal 
-lt is less than 
-le is less than 
-gt is greater than 
-ge is greater equal
'
#Kommentar zu: exit status:
: '
1   catchall for general errors, let "var1 = 1/0" 
2   misuse of shell builtins, according to Bash documentation
126 command invoked cannot execute 
127 "command not found" 
128 invalid argument to exit   
128+n   fatal error signal "n"  
130 script terminated by Control-C 
255*    exit status out of rang
'

case $1 in             #switch mit Parameter 1
    "-h")
        usage          #function call
        exit 0         #exit with no error
        ;;
    "")
        ask_for_name    #function call
        ;;
    *)
        user_name=$1    #variablen zuweisung
esac

#Kommentar zu: switch Anweisung:
: '
switch($1)
    case "-h":
        usage();
        exit 0;
        break;
    casw "":
        ask_for_name();
        break;
    default:
        user_name = $1;
        break;
'

# print greetings
echo "
####################
 Hello $user_name,
 nice to meet you! 
####################
"
#Kommentar zu: echo:
: '
std::cout << "####################" << endl;
std::cout << "Hello " << &user_name << "," << endl << "nice to meet you!" << endl;
std::cout << "####################" << endl;
'
exit 0          #exit with no error

# ---------------------- end ---------------------------------
