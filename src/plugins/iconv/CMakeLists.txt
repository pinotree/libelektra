if (DEPENDENCY_PHASE)
	find_package(Iconv)

	if (ICONV_FOUND)
		add_plugintest (iconv)
	else ()
		remove_plugin (iconv "Can't find iconv library")
	endif ()
endif ()

add_plugin (iconv
	SOURCES
		conv.h
		iconv.c
	INCLUDE_DIRECTORIES
		${ICONV_INCLUDE_DIR}	
	LINK_LIBRARIES
		${ICONV_LIBRARIES}
)
