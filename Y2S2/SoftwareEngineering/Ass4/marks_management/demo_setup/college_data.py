import sys
sys.path.append('../marks_management')

from college import AdminDB, SubjectDB, GradeDB

admin = AdminDB()
subject = SubjectDB()
grade = GradeDB()

# admin.drop_table()
admin.add_admin("admin", "1234")
admin.close_connection()

# subjects = ["OOS", "SE", "GGM", "Math", "CN", "GT"]
# for sub in subjects:
#     subject.add_subject(sub)

# subject.close_connection()

# grades = {'S': (90, 100), 'A': (80, 89), 'B': (70, 79), 'C': (60, 69), 'D': (50, 59), 'E': (40, 49), 'F' : (0, 39)}
# for g, (lower_bound, upper_bound) in grades.items():
#     grade.add_grade(g, lower_bound, upper_bound)

# grade.close_connection()
