import socket
import time

# Socket connection
def soc(res)->int:
    HOST = '127.0.0.1'  
    PORT = 65432        
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.bind((HOST, PORT))
        s.listen()
        conn, addr = s.accept()
        with conn:
            print('Connected by', addr)
            while True:
                d = conn.recv(1024)
                if not d:
                    break
                conn.sendall(res)
                y_b=int(d)
    return y_b

# Generator
def generator(al:int,p:int)->list[int]:
    l=[]
    for i in range(1,p):
        l.append(pow(al,i,p))
    return l

# Key generation
def keygen(p:int,al:int,x_a:int)->None:
    y_a=pow(al,x_a,p)
    y_a=str(y_a)
    res = bytes(y_a, 'utf-8')
    y_b=soc(res)
    k=pow(y_b,x_a,p)
    print("User A secret key is  ",k)

# Discrete logarithm
def dislog(y,a,p)->int:
    for i in range(p):
        if(pow(a,i,p)==y):
            return i
if __name__=='__main__':
    choice=0
    while(choice!=4):
        print("**********WELCOME TO DIFFIE HELLEMAN KEY EXCHANGE**********")
        print("SELECT YOUR CHOICE")
        print("1: Key Exchange , 2: Discreate logarthim , 3: Man in middle attack , 4.Exit")
        choice=int(input("Enter your choice: "))
        if(choice==4):
            print("**********Thankyou**********")
            break
        p=int(input("Enter ur prime no: "))
        al=int(input("Enter generator value: "))
        for i in range(p):
            al=int(input("Enter generator value: "))
            l=generator(al,p)
            if(len(l)==len(set(l))):
                print(al," is ur generator")
                break
        if(choice==1):
            x_a=int(input("Enter ur confidential no : "))
            keygen(p,al,x_a)
        if(choice==2):
            start=time.time()
            y=int(input("Enter y value: "))
            i=dislog(y,al,p)
            print("x value is: ",i)
            end=time.time()
            diff=end-start
            print("Total time taken is : ",diff)
        if(choice==3):
            x_c=int(input("Enter your fake confidential no : "))
            keygen(p,al,x_c)
        
        



    
