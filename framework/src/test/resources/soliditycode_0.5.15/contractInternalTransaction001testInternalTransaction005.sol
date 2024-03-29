//pragma solidity ^0.4.24;

contract A{
    constructor() payable public{}
    function() payable external{}
    function test1() public payable{
        B b1 = (new B).value(10)();//1.1
        b1.callCGetZero(false);
        b1.callCGetZero(true);//1.4
    }
    function test2() public payable{
        C c1 = (new C).value(10)();//1.1
        c1.newBAndTransfer(false);
        c1.newBAndTransfer(true);//1.4

    }
    function getBalance() view public returns(uint256){
        return address(this).balance;
    }
}

contract B{
    constructor() payable public{}
    function() payable external{}
    function getOne() payable public returns(uint256){
        return 1;
    }
    function callCGetZero(bool success) public payable{
        if(!success){
            assert(1==2);
        }
    }
    function getBalance() view public returns(uint256){
        return address(this).balance;
    }
}

contract C{
    uint256 public flag=0;
    constructor() payable public{}
    function() payable external{}
    function getZero() payable public returns(uint256){
        return 0;
    }
    function newBAndTransfer(bool success) payable public returns(uint256){
        flag = 1;
        if(!success){
        require(2==1);
        }
    }
    function getFlag() public returns(uint256){
        return flag;
    }
}