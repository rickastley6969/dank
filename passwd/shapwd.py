import hashlib
import itertools

def hashfunc(st):
    plaintext = st.encode()
    d = hashlib.sha256(plaintext)
    hash = d.hexdigest()
    return hash

s='ABCDEFGHIJKLMNOPQRSTUVWXYZ'
f= open("pwd.txt","w+")
t=list(itertools.permutations(s,2))

for i in range(0,len(t)):
    st=''.join(t[i])
    hash=hashfunc(st)
    sth=st+' '+hash
    f.write(" %s\n" % (sth))
f.close()

f1=open("pwd.txt","r")
lin=f1.readlines()
pwd=input("Enter your password: ")
hash1=hashfunc(pwd)
print("Hash of ur password is:  ",hash1)
print("*********APPLYING DICTIONARY ATTACK*********")
h=input("Enter your hash value: ")
for i in lin:
    n=len(i)
    stw=i[4:n-1]
    if(stw==h):
        print("Your password is" ,i[0:4])
        break
        
    
    
    
    
