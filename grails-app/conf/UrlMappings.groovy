class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        //"/results"(controller: "universal", action: "doSearchAll")
        "/"(view:"/index")
        "/results"(view: "/results")
        "500"(view:'/error')
	}
}
