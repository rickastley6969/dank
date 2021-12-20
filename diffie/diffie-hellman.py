import threading


def prelim():
    # Prime number
    prelim.P = int(input("Value for P: "))
    # Generator
    prelim.G = int(input("Value for G: "))

    print('The Value of P is :%d' % (prelim.P))
    print('The Value of G is :%d' % (prelim.G))


def getPrivateKeys():
    # Alice's private key
    getPrivateKeys.aP = int(input("The Private Key a for Alice is : "))
    # Bob's private key
    getPrivateKeys.bP = int(input("The Private Key b for Bob is : "))


def normal_flow():
    # generated keys for the sharing
    x = int(pow(prelim.G, getPrivateKeys.aP, prelim.P))
    y = int(pow(prelim.G, getPrivateKeys.bP, prelim.P))

    # Shared secret key generation
    ka = int(pow(y, getPrivateKeys.aP, prelim.P))
    kb = int(pow(x, getPrivateKeys.bP, prelim.P))

    print('Secret key for the Alice is : %d' % (ka))
    print('Secret Key for the Bob is : %d' % (kb))


def darth_flow():
    dP = int(input("Enter the private key of Darth: "))
    keyForA = int(pow(prelim.G, dP, prelim.P))
    keyForB = int(pow(prelim.G, dP, prelim.P))

    darth_flow.KeyGenA = int(pow(keyForB, getPrivateKeys.aP, prelim.P))
    darth_flow.keyGenB = int(pow(keyForA, getPrivateKeys.bP, prelim.P))

    if_darth(darth_flow.KeyGenA, darth_flow.keyGenB)


def if_darth(a, b):
    print("The private key of A as manipulated by darth: ", a)
    print("The private key of B as manipulated by darth: ", b)

    choice = input("Enter 1 to see the simulation else enter anything...")
    if(choice == "1"):
        darth_simulation(a, b)
    else:
        print("Thank you")


def darth_simulation(keyA, keyB):
    Message = input("Enter some message: ")

    cipher = caesar_encrypt(Message, keyA)
    print("Encryption by user A: ", cipher)

    decipher = caesar_decrypt(cipher, keyA)
    print("Decryption by Darth: ", decipher)

    cipher = caesar_encrypt(Message, keyB)
    print("Encryption by Darth: ", cipher)

    decipher = caesar_decrypt(cipher, keyB)
    print("Decryption by User B: ", decipher)


def caesar_encrypt(text1, key):
    word = text1
    c = ''
    for i in word:
        if (i == ' '):
            c += ' '
        else:
            c += (chr(ord(i) + int(key % 26)))
    return c


def caesar_decrypt(text2, key):
    word = text2
    c = ''
    for i in word:
        if (i == ' '):
            c += ' '
        else:
            c += (chr(ord(i) - int(key % 26)))
    return c


t1 = threading.Thread(target=prelim)
t2 = threading.Thread(target=getPrivateKeys)

t1.start()
t2.start()

t1.join()
t2.join()

flow = int(input("For darth sim enter 1... "))
if(flow == 1):
    t3 = threading.Thread(target=darth_flow)
    t3.start()
    t3.join()
else:
    t4 = threading.Thread(target=normal_flow)
    t4.start()
    t4.join()
