#ifndef CHECK_HPP
#define CHECK_HPP

#include <command.hpp>

#include <kdb>

class CheckCommand : public Command
{
public:
	CheckCommand();
	~CheckCommand();
	int execute(int argc, char**argv);
};

#endif