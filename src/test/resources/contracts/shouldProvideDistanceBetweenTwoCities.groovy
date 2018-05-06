org.springframework.cloud.contract.spec.Contract.make {
    request {
        method GET()

        url("/maps/api/distancematrix/json") {
            queryParameters {
                parameter 'origins': 'Berlin'
                parameter 'destinations': 'Stuttgart'
            }
        }
    }

    response {
        status 200
        body([
            rows: [[
                       elements: [[
                                      duration: [
                                          value: $(stub(22738), test(regex('\\d+')))
                                      ]
                                  ]]
                   ]]
        ])
    }
}
