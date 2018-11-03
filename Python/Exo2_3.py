from tkinter import *
from threading import Thread
from file import File
import time
import sys

#CONSOMMATEUR

class Producteur (Thread):
    def __init__(self,temps,file):
        Thread.__init__(self)
        self.file=file
        self.temps=temps
        self.interrupted=False
        self.texte = ""
    
    def stop(self):
        self.interrupted = True
    
    def run(self):
        while(not(self.interrupted)):
            self.texte= self.file.ajoute()
            time.sleep(self.temps)
    
    def getTexte(self):
        return self.texte

#-----------------------------------------------------



class Consommateur (Thread):
    def __init__(self,temps,file):
        Thread.__init__(self)
        self.file=file
        self.temps=temps
        self.interrupted = False
        self.texte=""
    
    def stop(self):
        self.interrupted = True

    def run(self):
        while(not(self.interrupted)):
            self.texte= self.file.supprime()
            time.sleep(self.temps)

    def getTexte(self):
        return self.texte




fenetre = Tk()

started = False

def start():
    global started
    started = True
    prod.start()
    cons1.start()
    cons2.start()

file = File()
prod = Producteur(0.3,file)
cons1 =  Consommateur(0.6,file)
cons2 =  Consommateur(0.6, file)
prod.setDaemon(True)
cons1.setDaemon(True)
cons2.setDaemon(True)


file_text=Label(fenetre, text="")
producteur=Label(fenetre,text="")
c1=Label(fenetre,text="")
c2=Label(fenetre,text="")
b=Button(fenetre , text="start" , command = start)

file_text.pack()
producteur.pack()
c1.pack()
c2.pack()
b.pack()


while(True):
    if (started):
        file_text["text"]=file.fileToString()
        producteur["text"]=prod.getTexte()
        c1["text"]=cons1.getTexte()
        c2["text"]=cons2.getTexte()

    fenetre.update()


    time.sleep(0.2)
    
    
    






