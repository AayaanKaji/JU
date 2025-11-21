#include <iostream>
#include <stdlib.h>

using namespace std;

class intArray {
  int size;
  int *arr;
  int index;

public:
  intArray(int x=0) : size(x), index(0) {
    arr = new int[size];
  }

  intArray(const intArray &a) : size(a.size), index(0) {
    arr = new int[size];
    for(int i=0; i<a.index; i++) {
      arr[index++] = a.arr[i];
    } 
  }

  ~intArray() {
    delete[] arr;
  }
  
  void insert(int x) {
    if(index >= size) {
      cout << "Array size limit exceeded." << endl;
      return;
    }
    arr[index++] = x;
  }

  int getSize() const {
    return size;
  }

  void print() const{
    for(int i=0; i<index; i++) {
      cout << arr[i] << " ";
    }
    cout << endl;
  }

  int &operator[](int i) const {
    if (i < 0 || i >= index) {
        throw out_of_range("Index out of bounds");
    }
    return arr[i];
  }

  friend intArray addArr(const intArray &a1, const intArray &a2);

  void reverse() {
    for (int i = 0; i < index / 2; i++) {
      int temp = arr[i];
      arr[i] = arr[index - i - 1];
      arr[index - i - 1] = temp;
    }
  }

  void sort() {
    for (int i = 0; i < index; i++) {
      for (int j = i + 1; j < index; j++) {
        if (arr[i] > arr[j]) {
          int temp = arr[i];
          arr[i] = arr[j];
          arr[j] = temp;
        }
      }
    }
  }
};

intArray addArr(const intArray &a1, const intArray &a2) {
  if(a1.size != a2.size) {
    throw invalid_argument("Array sizes must be equal for addition.");
  }

  intArray result(a1.size);
  for(int i=0; i<a1.index; i++) {
    result.insert(a1[i] + a2[i]);
  }
  return result;
}

int main() {

  intArray o1(3);
  for(int i=0; i<o1.getSize(); i++) {
    o1.insert(i+1);
  }

  intArray o2(o1);

  intArray o3 = addArr(o1, o2);
  o3.print();

  cout << "After reverse: ";
  o3.reverse();
  o3.print();

  cout << "After sorting: ";
  o3.sort();
  o3.print();
  return 0;
}