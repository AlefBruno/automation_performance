
   Demo Store Gatling 
=========================

    Abordagem padrão de testes de Performance:
        $mvn gatling:test -Dgatling.simulationClass=gatlingdemostore.simulations.PurchaseBasic

    Proposta baseada na Jornada dos Clientes:
        $mvn gatling:test -Dgatling.simulationClass=gatlingdemostore.simulations.PurchaseSimulations
