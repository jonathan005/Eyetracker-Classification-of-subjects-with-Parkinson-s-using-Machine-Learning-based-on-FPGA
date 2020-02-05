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
void create_csv(int cd){
FILE *fp;

fp=fopen("./real.csv","a+");
fprintf(fp,"%d\n",cd);
fclose(fp);
}
int main(void) {
	FILE* stream = fopen("C:\\Users\\USER\\Downloads\\Alldata.csv", "r");

	    char line[1024];
	    while (fgets(line, 1024, stream))
	    {
	    	static int control;
	        char* tmp = strdup(line);
	        char* tmp1 = strdup(line);
	        char* tmp2 = strdup(line);
	        //int x = getfield(tmp,4);
	        if(atoi(getfield(tmp,4))==1){
	        	control=100;
	        }if(atoi(getfield(tmp1,5))==1){
	        	control=10;
	        }if(atoi(getfield(tmp2,6))==1){
	        	control=1;
	        }
	        //int y = getfield(tmp1,5);
	        //int z = getfield(tmp2,6);
	        printf("%d\n",control);
	        // NOTE strtok clobbers tmp
	        free(tmp);
	        free(tmp1);
	        free(tmp2);
	        create_csv(control);
	    }
}
