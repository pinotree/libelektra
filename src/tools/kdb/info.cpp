/**
 * @file
 *
 * @brief
 *
 * @copyright BSD License (see doc/COPYING or http://www.libelektra.org)
 */

#include <info.hpp>

#include <cmdline.hpp>
#include <kdb.hpp>
#include <modules.hpp>
#include <plugin.hpp>

#include <iostream>


using namespace std;
using namespace kdb;
using namespace kdb::tools;


InfoCommand::InfoCommand ()
{
}

int InfoCommand::execute (Cmdline const & cl)
{
	std::string subkey;
	if (cl.arguments.size () == 1)
	{
	}
	else if (cl.arguments.size () == 2)
	{
		subkey = cl.arguments[1];
	}
	else
	{
		throw invalid_argument ("Need at 1 or 2 argument(s)");
	}
	std::string name = cl.arguments[0];

	KeySet conf;
	Key parentKey (std::string ("system/elektra/modules/") + name, KEY_END);

	if (!cl.load)
	{
		kdb.get (conf, parentKey);
	}

	if (!conf.lookup (parentKey))
	{
		if (!cl.load)
		{
			cerr << "Module does not seem to be loaded." << endl;
			cerr << "Now in fallback code. Will directly load config from plugin." << endl;
		}

		Modules modules;
		KeySet ks = cl.getPluginsConfig ();
		PluginPtr plugin;
		if (ks.size () == 0)
		{
			plugin = modules.load (name);
		}
		else
		{
			plugin = modules.load (name, ks);
		}
		conf.append (plugin->getInfo ());
	}

	Key root (std::string ("system/elektra/modules/") + name + "/exports", KEY_END);

	if (!subkey.empty ())
	{
		root.setName (std::string ("system/elektra/modules/") + name + "/infos/" + subkey);
		Key k = conf.lookup (root);
		if (k)
		{
			cout << k.getString () << std::endl;
			return 0;
		}
		else
		{
			cerr << "clause not found" << std::endl;
			return 1;
		}
	}

	root.setName (std::string ("system/elektra/modules/") + name + "/exports");
	Key k = conf.lookup (root);

	if (k)
	{
		cout << "Exported symbols: ";
		while ((k = conf.next ()) && k.isBelow (root))
		{
			cout << k.getBaseName () << " ";
		}
		cout << endl;
	}
	else
		cout << "no exported symbols found" << endl;

	root.setName (std::string ("system/elektra/modules/") + name + "/infos");
	k = conf.lookup (root);

	if (k)
	{
		while ((k = conf.next ()) && k.isBelow (root))
		{
			cout << k.getBaseName () << ": " << k.getString () << endl;
		}
	}
	else
		cout << "no information found" << endl;

	return 0;
}

InfoCommand::~InfoCommand ()
{
}
