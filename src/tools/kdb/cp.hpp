/**
 * @file
 *
 * @brief
 *
 * @copyright BSD License (see doc/COPYING or http://www.libelektra.org)
 */

#ifndef CP_HPP
#define CP_HPP

#include <command.hpp>

#include <kdb.hpp>

class CpCommand : public Command
{
	kdb::KDB kdb;

public:
	CpCommand ();
	~CpCommand ();

	virtual std::string getShortOptions () override
	{
		return "rv";
	}

	virtual std::string getSynopsis () override
	{
		return "<source> <dest>";
	}

	virtual std::string getShortHelpText () override
	{
		return "Copy keys within the key database.";
	}

	virtual std::string getLongHelpText () override
	{
		return "";
	}

	virtual int execute (Cmdline const & cmdline) override;
};

#endif
