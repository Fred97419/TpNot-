from random import randint
from threading import Condition

class File ():

    def __init__(self):
        self.file= []
        self.cond = Condition()
    

    def ajoute(self):
        with self.cond:
            nb_alea = randint(0,100)
            resultat=""
            if (len(self.file) ==20 ):
                self.cond.wait()
            else:
                self.file.append(nb_alea)
                self.cond.notify_all()
                resultat="Ajout de l'entier "+nb_alea+" ..."
                return resultat
            return resultat
    
    def supprime(self):
        with self.cond :
            resultat=""
            if (len(self.file) ==0):
                self.cond.wait()
            else :
                self.file.pop()
                self.cond.notify_all()
                resultat = "Retrait de "+self.file[0]+" effectue ! "
                return resultat
            return resultat


