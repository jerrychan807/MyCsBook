#include <stdio.h>

int add(int a,int b);


int main() {
    int a=0,b=0;
    printf("Please give a: ");
    scanf("%d", &a);
    printf("Please give b: ");
    scanf("%d", &b);
    int c = add(a,b);
    printf("Result a+b=%d\n", c);
}

int add(int a, int b) {
    return a;
}  
