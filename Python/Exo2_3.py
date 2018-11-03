from tkinter import *
from threading import Thread
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



def start():
    file = File()
    prod = Producteur(100,file)
    cons1 = new Consommateur(300,file)
    cons2 = new Consommateur(300, file)
    started = true
    prod.start()
    cons1.start()
    cons2.start()



fenetre = Tk()

file_text=Label(fenetre,text="test1")
producteur = Label(fenetre,text="test1")
c1 = Label(fenetre,text="test1")
c2 = Label(fenetre,text="test1")
bouton = Button(fenetre, text="Start", command=fenetre.quit)


file_text.pack()
producteur.pack()
c1.pack()
c2.pack()


fenetre.mainloop()




