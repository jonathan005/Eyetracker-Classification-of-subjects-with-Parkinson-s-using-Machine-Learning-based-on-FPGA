/*
 ============================================================================
 Name        : unir.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

const char* getfield(char* line, int num)
{
    const char* tok;
    for (tok = strtok(line, ",");
            tok && *tok;
            tok = strtok(NULL, ",\n"))
    {
        if (!--num)
            return tok;
    }
    return NULL;
}
void create_csv(int real, int predicho){
FILE *fp;

fp=fopen("./union2.csv","a+");
fprintf(fp,"%d,%d\n",real,predicho);
fclose(fp);
}
int main(void) {
	FILE* stream = fopen("C:\\Users\\USER\\Downloads\\PROYECTODSD\\SISTEMA\\k\\predicho.csv", "r");
	FILE* stream2 = fopen("C:\\Users\\USER\\Downloads\\PROYECTODSD\\SISTEMA\\lecturacsv\\real.csv", "r");
		    char line[1024];
		    char line2[1024];
		    while (fgets(line, 1024, stream) && fgets(line2,1024,stream2))
		    {
		    	char* tmp = strdup(line);
		    	char* tmp1 = strdup(line2);
		    	create_csv(atoi(getfield(tmp1,1)),atoi(getfield(tmp,1)));
		    }

}
