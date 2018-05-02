#define EVENT_SYSTEM_IMPLEMENTATION
#include "event/EventHub.hpp"
#include <iostream>
#include "JurPizza.hpp"
using namespace std;
struct Pizza {
 int i;
 Pizza() {
  i = 7;
 }
};

int main(int argc, const char** argv)
{

 Pizza p;
 
 EventHub::post(Pizza());
 EventHub::subscribe([](Pizza& pizza) -> void
 {  
  cout << pizza.i << endl;
 }
 ).setEternal();
 EventHub::post(Pizza());

 return JurPizza::startApp();
}