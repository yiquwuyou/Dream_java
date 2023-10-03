#define  _CRT_SECURE_NO_WARNINGS 1

#include <stdio.h>
int main()
{
    double score[5] = { 0 };
    int i = 0;
 
    while (i < 5)
    {
        int n = 0;
        while (n < 5)
        {
            scanf("%lf", &score[n]);
            n++;
        }
        n = 0;
        double sum = 0;
        while (n < 5)
        {
            printf("%.1lf ", score[n]);
            sum += score[n];
            n++;
        }
        printf("%.1lf\n", sum);
        sum = 0;
        i++;
    }
}