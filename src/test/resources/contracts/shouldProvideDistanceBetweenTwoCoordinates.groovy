org.springframework.cloud.contract.spec.Contract.make {
    request {
        method GET()

        url("/maps/api/distancematrix/json") {
            queryParameters {
                parameter 'origins': '52.52,13.4' // Berlin
                parameter 'destinations': '48.7758,9.1829' // Ludwigsburg
            }
        }
    }

    response {
        status 200

        body([
            rows  : [[
                         elements: [[
                                        distance: [
                                            value: $(stub(631782), test(regex('\\d+')))
                                        ],
                                        duration: [
                                            value: $(stub(22711), test(regex('\\d+')))
                                        ],
                                        status  : 'OK'
                                    ]]
                     ]],
            status: 'OK'
        ])

        headers {
            contentType(applicationJson())
        }
    }
}
