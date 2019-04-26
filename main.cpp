#include <iostream>
#include <fstream>

using namespace std;


class DjikstraSSS {
public:
    ifstream *in;
    ofstream *final;
    ofstream *debug;
    int numNodes;
    int sourceNode;
    int minNode;
    int currentNode;
    int newCost;
    int **costMatrix;
    int *fatherAry;
    int *markedAry;
    int *bestCostAry;

    DjikstraSSS(ifstream *in, ofstream *final, ofstream *debug) {
        this->in = in;
        this->final = final;
        this->debug = debug;
        (*in) >> numNodes;

        costMatrix = new int *[numNodes + 1];
        for (int i = 1; i < numNodes + 1; ++i) {
            costMatrix[i] = new int[numNodes + 1];
        }
        for (int j = 1; j < numNodes + 1; ++j) {
            for (int i = 1; i < numNodes + 1; ++i) {
                costMatrix[j][i] = 9999;
            }
            costMatrix[j][j] = 0;
        }
        fatherAry = new int[numNodes + 1];
        for (int k = 1; k < numNodes + 1; ++k) {
            fatherAry[k] = k;
        }
        markedAry = new int[numNodes + 1];
        for (int l = 1; l < numNodes + 1; ++l) {
            markedAry[l] = 0;
        }
        bestCostAry = new int[numNodes + 1];
        for (int m = 1; m < numNodes + 1; ++m) {
            bestCostAry[m] = 9999;
        }
    }

    void loadCostMatrix() {
        int row, col, cost;
        while(!(*in).eof()) {
            (*in) >> row >> col >> cost;
            costMatrix[row][col] = cost;
        }
    }

    void setBestCostAry() {
        for (int i = 1; i < numNodes + 1; ++i) {
            bestCostAry[i] = costMatrix[sourceNode][i];
        }
    }

    void setFatherAry() {
        for (int i = 1; i < numNodes + 1; ++i) {
            fatherAry[i] = sourceNode;
        }
    }

    void setMarkedAry() {
        for (int l = 1; l < numNodes + 1; ++l) {
            markedAry[l] = 0;
        }
        markedAry[sourceNode] = 1;
    }

    int findMinNode() {
        int minCost = 99999;
        minNode = 0;
        int index = 1;

        while (index <= numNodes) {
            if (markedAry[index] == 0) {
                if (bestCostAry[index] < minCost) {
                    minCost = bestCostAry[index];
                    minNode = index;
                }
            }
            index++;
        }
        return minNode;
    }

    void debugPrint() {
        (*debug) << "The source node is: " << sourceNode << endl;
        (*debug) << "The father array is: " << endl;
        for (int i = 1; i < numNodes + 1; ++i) {
            (*debug) << fatherAry[i] << " ";
        }
        (*debug) << "\n" << "The best Cost Array is: " << endl;
        for (int j = 1; j < numNodes + 1; ++j) {
            (*debug) << bestCostAry[j] << " ";
        }
        (*debug) << "\n" << "The marked Array is: " << "\n";
        for (int k = 1; k < numNodes + 1; ++k) {
            (*debug) << markedAry[k] << " ";
        }
        (*debug) << "\n" << "*************************" << "\n";
    }

    int computeCost() {
        int r = bestCostAry[minNode] + costMatrix[minNode][currentNode];
        return r;
    }

    bool isAllMarked() {
        for (int i = 1; i < numNodes + 1; ++i) {
            if (markedAry[i] == 0)
                return false;
        }
        return true;
    }

    void printShortestPath() {
        (*final) << "Source Node: " << sourceNode << endl << endl;
        (*final) << "The path from " << sourceNode << " to " << currentNode << " is: " << endl;
        int n = currentNode;
        (*final) << n;
        while (n != sourceNode) {
            (*final) << " <-- " << fatherAry[n];
            n = fatherAry[n];
        }

        (*final) << " cost: " << bestCostAry[currentNode] << endl;
    }

};

int main(int argc, const char *argv[]) {
    ifstream inFile(argv[1]);
    ofstream solution(argv[2]);
    ofstream debugging(argv[3]);
    DjikstraSSS operation(&inFile, &solution, &debugging);
    operation.loadCostMatrix();
    operation.sourceNode = 1;
    while (operation.sourceNode <= operation.numNodes) {
        operation.setBestCostAry();
        operation.setFatherAry();
        operation.setMarkedAry();
        while (!operation.isAllMarked()) {
            operation.minNode = operation.findMinNode();
            operation.markedAry[operation.minNode] = 1;
            operation.debugPrint();
            operation.currentNode = 1;
            while (operation.currentNode <= operation.numNodes) {
                if (operation.markedAry[operation.currentNode] == 0) {
                    operation.newCost = operation.computeCost();
                    if (operation.newCost < operation.bestCostAry[operation.currentNode]) {
                        operation.bestCostAry[operation.currentNode] = operation.newCost;
                        operation.fatherAry[operation.currentNode] = operation.minNode;
                        operation.debugPrint();
                    }
                }
                operation.currentNode++;
            }
        }

        operation.currentNode = 1;
        (*operation.final) << "==============================" << endl;
        (*operation.final) << "There are " << operation.numNodes
                           << " nodes in the input graph. Below are the all pairs of shortest paths:" << endl;
        (*operation.final) << "==============================" << endl;
        while (operation.currentNode <= operation.numNodes) {
            operation.printShortestPath();
            operation.currentNode++;
        }
        operation.sourceNode++;
    }
    (*operation.final).close();
    (*operation.debug).close();
    (*operation.in).close();
    return 0;
}