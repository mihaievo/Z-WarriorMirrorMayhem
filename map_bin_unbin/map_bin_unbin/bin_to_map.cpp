/*#include <iostream>
#include <fstream>

#define MAP_SIZE_H 6400
#define MAP_SIZE_V 720

using namespace std;

int main(void)
{
    ifstream fin("map1.bin");
    ofstream fout("map1_datadecode.txt");

    unsigned char num_to_bin;

    int num;
    while (!fin.eof())
    {
        num_to_bin = fin.get();
        num = (int)num_to_bin;
        //cout << "READ: "<< num << " -> " << num_to_bin << endl;
        if (num_to_bin == '!')
        {
            cout << '\n';
        }
        else
            cout << num << " ";
    }
}*/