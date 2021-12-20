def numenc(pt,n):
    st='abcdefghijklmnopqrstuvwxyz'
    l=[]
    ptl=[]
    ji=1
    for i in pt:
        num=st.index(i)
        l.append(num)
        if(ji==n):
            ptl.append(l)
            ji=0
            l=[]
        ji=ji+1
    ptnum = [[ptl[j][i] for j in range(len(ptl))] for i in range(len(ptl[0]))]
    return ptnum
def charenc(fnmat):
    st='abcdefghijklmnopqrstuvwxyz'
    fmat=[[fnmat[j][i] for j in range(len(fnmat))] for i in range(len(fnmat[0]))]
    stword=" "
    for i in fmat:
        for j in i:
            stword+=st[j]
    print(stword)
def scalarmul(matrix,m):
    n=len(matrix)
    l3=[]
    l4=[]
    for i in range(n):
        for j in range(n):
            p=matrix[i][j]*m
            print(p)
            p=p%26
            if(p<0):
                p=p+26
            l3.append(p)
        l4.append(l3)
        l3=[]
    return l4
        
def matmul(a,b):
    n=len(a)
    if(n==3):
        result = [[0, 0, 0],[0, 0, 0],[0, 0, 0]]
    else:
        result=[[0,0],[0,0]]
    for i in range(n):
        for j in range(len(b[0])):
            
            for k in range(n):
                result[i][j]+=a[i][k]*b[k][j]
    for i in range(n):
        for j in range(n):
            result[i][j]=result[i][j]%26
            if(result[i][j]<0):
                result[i][j]=result[i][j]+26
    return result
def getcofactor(m, i, j):
    return [row[: j] + row[j+1:] for row in (m[: i] + m[i+1:])]
def determinantOfMatrix(mat):
    if(len(mat) == 2):
        value = mat[0][0] * mat[1][1] - mat[1][0] * mat[0][1]
        return value
    Sum = 0
    for current_column in range(len(mat)):
        sign = (-1) ** (current_column)        
        sub_det = determinantOfMatrix(getcofactor(mat, 0, current_column))
        Sum += (sign * mat[0][current_column] * sub_det)
    Sum=int(Sum%26)
    if(Sum<0):
        Sum=Sum+26
    return Sum
def euclids(b):
    a=26
    q=[]
    tt=[]
    while(b!=0):
        qn=int(a/b)
        q.append(qn)
        rem=a%b
        temp=b
        b=rem
        a=temp
    if(b==0):
        t1=0
        t2=1
        nn=len(q)-1
        for i in q:
            t=int(t1-(i*t2))
            t1=t2
            t2=t
            tt.append(t2)
    val=tt[nn-1]
    if(val<0):
        val=val+26
    return val
            
def adj(a):
    sign=1
    n=len(a)
    temp=[]
    l1=[]
    l2=[]
    adjmat=[]
    rez2=[]
    l_2=[]
    if(n==3):
        for i in range(n):
            for j in range(len(a[0])):
                sign = (-1) ** (j)
                if(i==1 or i%2!=0):
                    sign=sign*(-1)
                l=getcofactor(a,i,j)
                value = l[0][0] * l[1][1] - l[1][0] * l[0][1]
                value=sign * value
                l1.append(value)
            l2.append(l1)
            l1=[]
        rez = [[l2[j][i] for j in range(len(l2))] for i in range(len(l2[0]))]
    else:
        l_2.append(a[1][1])
        adj_2=(-1)*a[0][1]
        l_2.append(adj_2)
        l2.append(l_2)
        l_2=[]
        adj_2=(-1)*a[1][0]
        l_2.append(adj_2)
        l_2.append(a[0][0])
        l2.append(l_2)
        rez=l2
    rez1=[]
    for i in range(n):
        for j in range(n):
            k=rez[i][j]
            k1=int(k%26)
            if(k1<0):
                k1=k1+26
            rez1.append(k1)
        rez2.append(rez1)
        rez1=[]
    return rez2

if __name__ == '__main__':
    print('**********HILL CIPHER**********')
    print("1. Encrypt 2. Decrypt, 3. Known pt-ct")
    choice=int(input("enter ur choice: "))
    if(choice==1):
        pt=input("enter plain text: ")
        key=eval(input("enter ur key value: "))
        s=list(key)
        n=len(s)
        tenc=numenc(pt,n)
        mul=matmul(s,tenc)
        for rrr in mul:
            print(rrr)
        charenc(mul)
    if(choice==2):
        ct=input("enter your cipher text")
        s=eval(input("enter ur key value: "))
        key=list(s)
        n=len(key)
        det=(determinantOfMatrix(key))
        print(det)
        mi=euclids(det)
        print(mi)
        adjoint=adj(key)
        for row in adjoint:
            print(row)
        inv=scalarmul(adjoint,mi)
        for rows in inv:
            print(rows)
        tenc=numenc(ct,n)
        mul=matmul(inv,tenc)
        for rr in mul:
            print(rr)
        charenc(mul)
    if(choice==3):
        pt=input("enter plain text: ")
        ct=input("enter your cipher text: ")
        if(len(pt)==4):
            n=2
        elif(len(pt)==9):
            n=3
        elif(len(pt)!=4 or len(pt)!=9):
            pt=pt[:5]
            ct=ct[:5]
            n=2
        tenc=numenc(pt,n)
        ctenc=numenc(ct,n)
        det=(determinantOfMatrix(ctenc))
        mi=euclids(det)
        adjoint=adj(ctenc)
        inv=scalarmul(adjoint,mi)
        mul=matmul(tenc,inv)
        print("your key value is")
        for row in mul:
            print(row)
        
        
            
            
    
