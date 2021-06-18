=========================
   Demo Store Gatling 
=========================

    Abordagem padrão de testes de Performance:
        $mvn gatling:test -Dgatling.simulationClass=gatlingdemostore.simulations.PurchaseBasic

    Proposta de Execucão baseada em Jornadas dos seus Clientes
        $mvn gatling:test -Dgatling.simulationClass=gatlingdemostore.simulations.PurchaseSimulations
