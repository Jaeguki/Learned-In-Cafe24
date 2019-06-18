# -*- coding: utf-8 -*-

from bs4 import BeautifulSoup
import urllib.request
import urllib.parse


def ex1():
    url = input("도메인 주소를 입력하세요 >> ")
    # https://movie.naver.com/movie/sdb/rank/rmovie.nhn
    html = urllib.request.urlopen(
        urllib.request.Request(url)
    ).read()

    bs = BeautifulSoup(html, 'html.parser')

    # print(bs.prettify())

    div_list = bs.findAll('div', attrs={'class': 'tit3'})
    print(div_list)

    for index, div in enumerate(div_list):
        print(index, div.a.text, div.a['href'])


def crawling_nene():
    # store = input("지역명 검색시 매장이 안내됩니다.")
    # url = "https://nenechicken.com/17_new/sub_shop01.asp?IndexSword={0}&GUBUN=A".format(urllib.parse.quote_plus(store))
    url = "https://nenechicken.com/17_new/sub_shop01.asp"
    print(url)
    html = urllib.request.urlopen(
        urllib.request.Request(url)
    ).read()

    bs = BeautifulSoup(html, 'html.parser')
    start_page_index = 1
    print(bs.findAll('span', attrs={'class': 'page_noselect'}))
    last_page_index_string = str(bs.findAll('span', attrs={'class': 'page_noselect'}))
    last_page_index = int(
        last_page_index_string[last_page_index_string.find('>')+1: last_page_index_string.rfind('<')]
    ) + 1
    print('find last page index', last_page_index)
    print('page range ({0}, {1})'.format(start_page_index, last_page_index))
    nene_shop_list = {}

    for index in range(start_page_index, last_page_index):
        print('show page index', index)
        request_url = "https://nenechicken.com/17_new/sub_shop01.asp?page={0}".format(index)
        html = urllib.request.urlopen(
            urllib.request.Request(request_url)
        )

        bs = BeautifulSoup(html, 'html.parser')
        shop_table_list = bs.findAll('table', attrs={'class': 'shopTable'})
        for shop_table in shop_table_list:

            shop_name = shop_table.find('div', attrs={'class': 'shopName'})
            shop_add = shop_table.find('div', attrs={'class': 'shopAdd'})
            shop_tel = shop_table.find('span', attrs={'class': 'tooltiptext'})
            print(shop_name.get_text(), shop_add.get_text(), shop_tel.get_text())



if __name__=="__main__":
    # ex1()
    crawling_nene()


