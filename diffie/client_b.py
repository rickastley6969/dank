import socket
def soc(res)->int:
    HOST = '127.0.0.1'  
    PORT = 65432        
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.connect((HOST, PORT))
        s.sendall(res)
        data = s.recv(1024)
    y_a=int(data)
    return y_a

def keygen(p,al,x_b)->None:
    y_b=pow(al,x_b,p)
    y_b=str(y_b)
    res = bytes(y_b, 'utf-8')
    y_a=soc(res)
    k=pow(y_a,x_b,p)
    print("User B secret key is  ",k)
if __name__=='__main__':
    choice=0
    while(choice!=4):
        print("**********WELCOME TO DIFFIE HELLEMAN KEY EXCHANGE**********")
        print("SELECT YOUR CHOICE")
        print("1: Key Exchange , 2: Discreate logarthim , 3: Man in middle attack, 4: Exit")
        choice=int(input("Enter your choice: "))
        if(choice==4):
            print("**********Thankyou**********")
            break
        p=int(input("Enter ur prime no: "))
        al=int(input("Enter ur generator: "))
        if(choice==1):
            x_b=int(input("Enter ur confidential no : "))
            keygen(p,al,x_b)
        if(choice==3):
            x_d=int(input("Enter your fake confidential no : "))
            keygen(p,al,x_d)
            
