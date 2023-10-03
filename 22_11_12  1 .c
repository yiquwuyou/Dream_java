#define  _CRT_SECURE_NO_WARNINGS 1
#include <stdio.h>
int da(int, int);
int main()
{
    int a = 0;
    int b = 0;
    int c = 0;
    scanf("%d %d", &a, &b);
    printf("%d\n", c = da(a, b));
    return 0;
}


int da(int a, int b)
{
    if (a >= b)
        return a;
    else
        return b;
}