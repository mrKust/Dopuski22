import matplotlib.pyplot as plt


def lineplotMD(x_data, y_data, y2_data, x_label="", y_label="", title=""):
    _, ax = plt.subplots()
    ax.plot(x_data, y_data, 'g', lw=2, label=('Моделируемое'))
    ax.plot(x_data, y2_data, 'b--', lw=2, label=('По формуле'))
    ax.set_title(title)
    ax.set_xlabel(x_label)
    ax.set_ylabel(y_label)


def main():
    data = []
    with open("D:\Pereezd\Labs\Тюрликов\model.txt") as f:
        for line in f:
            data.append([float(x) for x in line.split()])

    print(data)
    dataX = []
    dataY1 = []
    dataY2 = []


    for line in data:
        dataX.append(line.pop(0))
        dataY1.append(line.pop(0))
        dataY2.append(line.pop(0))

    print(dataX)
    print(dataY1)
    print(dataY2)


    lineplotMD(dataX, dataY1, dataY2, "t", "Вероятность", "Значения")
    plt.legend()
    plt.show()


if __name__ == '__main__':
    main()
