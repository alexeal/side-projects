#!/usr/bin/python
# -*- coding: iso-8859-1 -*-
# Alexandre Ravaux
# Ninn version 0

import sys
import Tkinter
# Window frame (root)
windowFrame=Tkinter.Tk()
windowFrame.title('Ninn 0.1 version Python Tk')
# Textarea
textarea=Tkinter.Text(windowFrame)
# Scrollbar
scrollbar = Tkinter.Scrollbar(windowFrame)

# scrollbar on the right
scrollbar.pack(side=Tkinter.RIGHT, fill=Tkinter.Y)
# textarea on the left
textarea.pack(side=Tkinter.LEFT, fill=Tkinter.Y)

scrollbar.config(command=textarea.yview)
textarea.config(yscrollcommand=scrollbar.set)

def ecran(var): # fonction servant a l'affichage du textarea:
  textarea.insert(Tkinter.END,var)

menubar = Tkinter.Menu(windowFrame) # Creation du systeme de menu (variable explicite menubar):

menu1 = Tkinter.Menu(menubar) # Creation du premier menu:
menubar.add_cascade(label="File", menu=menu1)

# addition des deux items pour le deuxième menu et leur commande associee
menu1.add_command(label="Save", command=lambda: ecran('Not implemented\n'))
menu1.add_command(label="Quit",command=lambda:ecran('Not implemented\n'))

menu2 = Tkinter.Menu(menubar) # Creation du second menu
menubar.add_cascade(label="Edit", menu=menu2)

# addition des deux items pour le deuxième menu et leur commande associee
menu2.add_command(label="Font", command=lambda: ecran('Not implemented\n'))
menu2.add_command(label="Size",command=lambda:ecran('Not implemented\n'))
windowFrame.config(menu=menubar)
windowFrame.mainloop()
