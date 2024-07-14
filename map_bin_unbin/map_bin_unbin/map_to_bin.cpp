#include <iostream>
#include <fstream>
#include <vector>

#define MAP_SIZE_H 6400
#define TILES_HSIZE 16

using namespace std;

// Function to encode integer numbers to binary and write to file
void encodeToBinary(const string& inputFileName, const string& outputFileName) {
    ifstream fin(inputFileName);
    if (!fin) {
        cerr << "Error: Unable to open input file." << endl;
        return;
    }

    ofstream fout(outputFileName, ios::binary);
    if (!fout) {
        cerr << "Error: Unable to create binary output file." << endl;
        fin.close();
        return;
    }

    int num;
    while (fin >> num) {
        // Check if number is within the valid range to be encoded as char
        if (num >= -128 && num <= 127) {
            char byte = static_cast<char>(num);
            fout.write(&byte, sizeof(char));
        }
        else {
            cerr << "Error: Number out of valid range (-128 to 127)." << endl;
            fin.close();
            fout.close();
            return;
        }
    }

    fin.close();
    fout.close();
}

// Function to decode binary data from file and write to text file
void decodeFromBinary(const string& inputFileName, const string& outputFileName) {
    ifstream fin(inputFileName, ios::binary);
    if (!fin) {
        cerr << "Error: Unable to open binary input file." << endl;
        return;
    }

    ofstream fout(outputFileName);
    if (!fout) {
        cerr << "Error: Unable to create output file for decoded text." << endl;
        fin.close();
        return;
    }

    char byte;
    int ctr = 0;
    while (fin.read(&byte, sizeof(char))) {
        // Interpret the char byte as an integer to preserve negative values
        int num = static_cast<int>(static_cast<unsigned char>(byte)); // Cast to unsigned char first
        if (num > 127) {
            num -= 256; // Convert to signed char value if needed
        }
        fout << num << " ";
        ctr++;
        if (ctr == MAP_SIZE_H / TILES_HSIZE) {
            fout << endl;
            ctr = 0;
        }
    }

    fin.close();
    fout.close();
}

int main() {
    const string inputFileName = "map1_data.txt";
    const string binaryFileName = "map1.bin";
    const string decodedTextFileName = "map1_decoded.txt";

    // Encode integer numbers from input text file to binary
    encodeToBinary(inputFileName, binaryFileName);

    // Decode binary data back to text and write to output text file
    decodeFromBinary(binaryFileName, decodedTextFileName);

    return 0;
}
