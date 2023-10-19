/*			Copyright Q Solutions				*/
/*	File:		testmag.c					*/
/*	Programmer:	W.A						*/
/*	Module:		Hardware test program                   	*/
/*									*/
/*			History						*/
/* 10:00 06/05/1998 	Written from scratch				*/
/*			Initialisation and main loop.			*/
/*									*/
/******************************************************************************/
#pragma	ROM (COMPACT)
#pragma	LARGE
/******************************************************************************/
/* Standard Library Header Files					*/

#include <stdio.h>
//#include <reg552.h>

/***************************************************************************/

/* uNode Developer's Kit Library Header Files				*/
#include <wdog.h>			/* Watchdog refresh routine	*/
#include <serial.h>			/* Serial port routines 	*/
#include <types.h>			/* Header with all defines      */

//!!!
//#include "adc.h"			/* Analog Converter routines 	*/

#include "adc.c"			/* !!!!      */

#define IO_SER 0x02			/* Output to serial 		*/
#define XDIGIT ((char *) 0x2ffffL)	/* Pointer to Digital Port	*/

/*			ADC Parameters					*/
#define	AVE_CHAN	4		/* ADC channels to average	*/
#define	SAMPLE_MAX	32		/* Number of ADC samples	*/

char	out_stream;			/* Output port = Serial port	*/
char	read_digit;			/* Digital Sample		*/
uint	adc_samples[AVE_CHAN]; 		/* Samples holding registers	*/
uint	adc_tx[AVE_CHAN];		/* Last sample result		*/

sbit cshw1 = 0xB4;			/* Chip select for digital read	*/
sbit cshw2 = 0xB5;			/* Chip select for digital read	*/

void	init_xdigit()			/* Set up digital inputs	*/
{
	cshw1 = 1;			/* Set up port pins 		*/
        cshw2 = 0;			/* See design notes		*/
	*XDIGIT = 0x00;
	*XDIGIT = 0x00;
	cshw2 = 1;
}

void	initialise ( )			/* Initialise Hardware		*/
{
 	EA = 1;				/* Enable Global Interrupt	*/
        init_xdigit();			/* Set up digital port		*/
        out_stream = IO_SER;		/* IO = serial port		*/
        init_serial ( B9600 );		/* Initialise Serial Port 	*/
        init_adc ();			/* Initialise Analog -> Digital */
}

void	read_digital()			/* Read digital port		*/
{
	cshw2 = 0;			/* Enable digital latch		*/
	read_digit = *XDIGIT;		/* Get data			*/
	cshw2 = 1;			/* Disable digital latch	*/
}

char	putchar ( char out )
{
	char result;
	switch ( out_stream )
	{
        case IO_SER:			/* Write to local serial port	*/
                       result = sputchar ( out );
		       break;
        default:       return (-1);
	}
	return ( result );
}

void	calc_ave ( )
{
byte	i;
	for	( i= 0; i< AVE_CHAN; i++ )
	{
		adc_samples[i] /= SAMPLE_MAX;
        	adc_tx[i] = adc_samples[i];         
		adc_samples[i] = 0;
	}
}

void	main ( void )			/* Main Loop			*/
{
byte	i, j;
        initialise ( );		  	/* Set up drivers, sign on	*/
      	while ( 1 )
       	{
		for	( i= 0; i< SAMPLE_MAX; i++ )
		{
			wdog(10);
			adc_start();	/* Start sampling channels	*/
			while(adc_read);/* Wait for converter		*/
			for	( j= 0; j< AVE_CHAN; j++ )
				adc_samples[j]+= adc10[j];
		}
		calc_ave( );		/* Calculate average ADC reading*/
		while	( s_run )	/* Prevent serial buff overflow	*/
			wdog(10);
		read_digital();		/* Get digital port data	*/
		for	( i = 0; i < AVE_CHAN; i++)	/* Send ADC data*/
			printf("%04u ",adc_tx[i]);         
		printf("%04bu \n", read_digit);    /* Send Digital data	*/
	}
}