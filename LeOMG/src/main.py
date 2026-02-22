import LeoAccess
import sys

def process(text):
    return LeoAccess.search(text)

if __name__ == "__main__":
    user_input = sys.stdin.readline().strip()
    result = process(user_input)
    print(result)