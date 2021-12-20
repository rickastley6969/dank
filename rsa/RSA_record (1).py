def encrsa(m,e,n):
    st='abcdefghijklmnopqrstuvwxyz'
    for j in m:
        s=1
        num=st.index(j)
        for i in range(e):
            s=s*num
        res=s%n
        print("Encrypted value of ",j,"is ", res)
def euclids(a,b):
    if(a<b):
        temp =a
        a=b
        l=[]
        b=temp
    while(b!=0):
        q=a//b
        r=a%b
        a=b
        b=r
        l.append(q)
    if(a==1):
        return 1,l
    else:
        return 0,l
def exteuc(q1,phi):
    t1=0
    t2=1
    tt=[]
    nn=len(q1)-1
    for i in q1:
        t=int(t1-(i*t2))
        t1=t2
        t2=t
        tt.append(t2)
    val=tt[nn-1]
    d=val % phi
    return d
def decrsa(li,d,n):    
    for j in li:
        s=1
        for i in range(d):
            s=(s*j)
        res=s%n
        print("Decrypted value of ",j,"is ", res)
def keygen():
    p=int(input("enter ur 1st value: "))
    q=int(input("enter ur 2nd value: "))
    n=p*q
    phi_n=(p-1)*(q-1)
    earr=[3,5,7,11,13,17,19]
    q1=[]
    for i in range(len(earr)):
        bn,q1=euclids(earr[i],phi_n)
        if(bn==1):
            e=earr[i]
            break
    return e,n,phi_n,q1;
if __name__=="__main__":
    ch=0
    while(ch!=3):
        print("**********welcome to RSA**********")
        print("1) Encryprion 2) Decryption 3)exit ")
        ch=int(input("enter ur choice: "))
        if(ch==1):
            print("**********ENC**********")
            print("Enter your message to be encrypted: ")
            m=input()
            e,n,phi,q1=keygen()
            print("Your public key is (",e,",",n,")")
            encrsa(m,e,n)
        if(ch==2):
            print("**********DEC**********")
            li=eval(input("Enter your cipher values: "))
            e,n,phi,q1=keygen()
            d=exteuc(q1,phi)
            print("Your private key is (",d,",",n,")")
            decrsa(li,d,n)
        if(ch==3):
            print("**********Thankyou for choosing RSA**********")
        
