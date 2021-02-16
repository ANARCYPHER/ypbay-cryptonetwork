var YpbayToken = artifacts.require("./YpbayToken.sol");


module.exports = function(deployer) {  
    deployer.deploy(YpbayToken); 
};
