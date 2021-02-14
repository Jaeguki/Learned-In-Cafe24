# -*- coding: utf-8 -*-
import re
from random import *


def example_01():
    # 문제1.
    # 파이썬 경로명 s = '/usr/local/bin/python' 에서 각각의 디렉토리 경로명을 분리하여 출력하세요.
    python_path = '/usr/local/bin/python'
    path_split_list = python_path[1:].split('/')
    print(path_split_list)

    # 또, 디렉토리 경로명과 파일명을 분리하여 출력하세요.
    dir_path = python_path[:python_path.rfind("/")]
    file_name = python_path[python_path.rfind("/") + 1:]
    print(dir_path, file_name)


def example_02():
    # 문제2.
    # 다음과 같은 텍스트에서 모든 태그를 제외한 텍스트만 출력하세요.
    html_data = """
    <html>
        <body style='background-color:#ffff'>
            <h4>Click</h4>
            <a href='http://www.python.org'>Here</a>
            <p>
                To connect to the most powerful tools in the world.
            </p>
        </body>
    </html>"""
    while html_data.find('<') != -1:
        html_data = html_data.replace(html_data[html_data.find('<'):html_data.find('>') + 1], '')
    print(html_data)
    # pattern = re.compile("[<]*[>]")


def example_03():
    # 문제3.
    #
    # 1)다음 문자열을 모든 소문자를 대문자로 변환하고, 문자 ',', '.','\n'를 없앤 후에 중복
    # 없이 각 단어를 순서대로 출력하세요.
    s = """We encourage everyone to contribute to Python. If you still have questions after reviewing the material
    in this guide, then the Python Mentors group is available to help guide new contributors through the process."""

    word_list = list(set(s.upper().replace(',', '').replace('.', '').replace('\n', '').split(' ')))
    word_list.sort()
    for word in word_list:
        print(word)

    # 2)각 단어의 빈도수도 함께 출력해 보세요
    for word in word_list:
        print(word, ":", s.upper().count(word))


def example_04():
    # 문제4
    # 반복문을 이용하여 369게임에서 박수를 쳐야 하는 경우의 수를 순서대로 화면에
    # 출력해보세요. 1부터 99까지만 실행하세요.
    num = 100
    for i in range(0, num):
        cnt = 0
        if (str(i).find("3") > -1) | (str(i).find("6") > -1) | (str(i).find("9") > -1):
            cnt += str(i).count("3")
            cnt += str(i).count("6")
            cnt += str(i).count("9")
            print(i, "짝" * cnt)


def example_05():
    # 문제5.
    # 함수 sum 을 만드세요. 이 함수는 임의의 개수의 인수를 받아서 그 합을 계산합니다.
    def sum(*argv):
        result = 0
        for i in range(0, len(argv)):
            result += argv[i]
        print(result)

    sum(1, 2, 3, 4, 5, 6)


def example_06():
    # 문제6
    # 숨겨진 카드의 수를 맞추는 게임입니다.
    # 1-100까지의 임의의 수를 가진 카드를 한 장 숨기고 이 카드의 수를 맞추는 게임입니다.
    # 아래의 화면과 같이 카드 속의 수가 57인 경우를 보면 수를 맞추는 사람이 40이라고 입력하면 "더 높게", 다시 75이라고 입력하면 "더 낮게" 라는 식으로 범위를 좁혀가며 수를 맞추고 있습니다.
    # 게임을 반복하기 위해 y/n이라고 묻고 n인 경우 종료됩니다.

    num = randint(1, 100)  # 1부터 100 사이의 임의의 정수
    print("수를 결정하였습니다. 맞추어 보세요. 정답 :", num)
    print("1-100")
    answer = "y"
    cnt = 1
    while answer.lower() != "n":
        input_number = int(input(str(cnt) + ">>"))
        if num == input_number:
            answer = input("맞았습니다.\n다시 하시겠습니까?(y/n)>>")
            if answer == "y":
                print("수를 결정하였습니다. 맞추어 보세요.", num)
                print("1-100")
                cnt = 1
        else:
            if num < input_number:
                print("더 낮게 입력하세요.")
            elif num > input_number:
                print("더 높게 입력하세요.")
        cnt += 1


if __name__ == "__main__":
    example_01()
    example_02()
    example_03()
    example_04()
    example_05()
    example_06()

