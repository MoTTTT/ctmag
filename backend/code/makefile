all :

clear :
	del *.obj
	del *.abs
	del *.hex
	del *.lst

clean :
	del *.obj
	del *.abs
	del *.hex
	del *.bin
	del *.lst

dlmag :
	c:\dosemu\unix dd if=~/working/ctmag/backend/code/testmag1.bin of=/dev/cua1

ctmag.bin : ctmag.hex

ctmag.hex : ctmag.abs

ctmag.abs : ctmag.obj
	bl51 ctmag.obj,nodedev.lib to ctmag.abs ${LFLAGS} XDATA(4200H)

ctmag.obj : ctmag.c;

ctmag :
	make clear
	del ctmag.bin
	make ctmag.obj
	make ctmag.abs
	make ctmag.hex
	make ctmag.bin
	make clear

