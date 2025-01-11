#include <iostream>
#include <random>
#include <string>
using namespace std;

class MarksList {
	int* data;
	static int size;
	float cgpa;
public:
	MarksList(int *val=nullptr) {
		int sum = 0;
		data = new int[size];
		if(val != nullptr) {
			for(int i=0; i<size; i++) {
				data[i] = val[i];
				sum += val[i];
			}
		}
		cgpa = (float)(sum / 40);
	}

	float getCGPA() const {
		return cgpa;
	}
};

int MarksList::size = 4;

class GradeCard {
	string dept;
	int sem;
	string name;
	int roll;
	static int rollInc;
	MarksList mlist;
	

public:
	GradeCard(string d="Default", int sem=3, string n="S0", int marks[]=0)
	: sem(sem), roll(rollInc++), mlist(marks)
	{
		dept = d;
		name = n;
	}

	string getName() const {
		return name;
	}

	int getRoll() const {
		return roll;
	}

	float getCGPA() const {
		return mlist.getCGPA();
	}
};

int GradeCard::rollInc = 1;

int main()
{

	mt19937 mt(50);
    uniform_int_distribution<int> dist(40, 90);
	
    const int num = 60;
    int sem = 3;
	GradeCard Students[num];
	string dept[6] = {"CSE", "CE", "EE", "ETCE", "IT", "ME"};
	for(int i=0; i<num; i++) {
		string s = "S" + to_string(i+1);
		int d_id = i % 6;
		string d = dept[d_id];
		int marks[4];
		for(int i=0; i<4; i++) {
			marks[i] = dist(mt);
		}
		Students[i] = GradeCard(d, sem, s, marks);
	}

	float maxGPA = 0;

	for(int i=0; i<num; i++) {
		float x = Students[i].getCGPA();
		maxGPA = (maxGPA < x) ? x : maxGPA;
	}
	
	cout << "student_name"  << " " << "student_roll" << " " << "student_cgpa" << endl;

	for(int i=0; i<num; i++) {
		float gpa = Students[i].getCGPA();

		if(gpa == maxGPA) {
			cout << "      " << Students[i].getName() << "            " 
			     << Students[i].getRoll() << "              "  
			     << Students[i].getCGPA() << endl;
		}
	}
}