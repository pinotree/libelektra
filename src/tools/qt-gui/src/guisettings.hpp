/**
 * @file
 *
 * @brief
 *
 * @copyright BSD License (see doc/COPYING or http://www.libelektra.org)
 */

#ifndef GUISETTINGS_HPP
#define GUISETTINGS_HPP

#include <QColor>
#include <QObject>
#include <kdb.hpp>

/**
 * @brief The GUISettings class. It manages the settings of the GUI. At the moment the only settings are colorsettings.
 */
class GUISettings : public QObject
{
	Q_OBJECT

	Q_PROPERTY (QColor highlightColor READ highlightColor () WRITE setHighlightColor (QColor) NOTIFY highlightColorChanged ())
	Q_PROPERTY (QColor frameColor READ frameColor () WRITE setFrameColor (QColor) NOTIFY frameColorChanged ())
	Q_PROPERTY (QColor nodeWithKeyColor READ nodeWithKeyColor () WRITE setNodeWithKeyColor (QColor) NOTIFY nodeWithKeyColorChanged ())
	Q_PROPERTY (QColor nodeWithoutKeyColor READ nodeWithoutKeyColor () WRITE setNodeWithoutKeyColor (QColor)
			    NOTIFY nodeWithoutKeyColorChanged ())

public:
	/**
	 * @brief GUISettings The default constructor
	 * @param parentGUISettings
	 */
	explicit GUISettings (QObject * parentGUISettings = nullptr);

	/**
	 * @brief GUISettings The copy contructor. Mandatory.
	 * @param other
	 */
	GUISettings (GUISettings const & other) : QObject ()
	{
		Q_UNUSED (other)
	}

	/**
	 * @brief highlightColor The color of the highlight bar in the views.
	 * @return The color of the highlight bar in the views.
	 */
	QColor highlightColor () const;

	/**
	 * @brief frameColor The color of the window frames in the views.
	 * @return The color of the window frames in the views.
	 */
	QColor frameColor () const;

	/**
	 * @brief nodeWithKeyColor The color of the ConfigNodes that contain a kdb::Key
	 * @return The color of the ConfigNodes that contain a kdb::Key
	 */
	QColor nodeWithKeyColor () const;

	/**
	 * @brief nodeWithoutKeyColor The color of the ConfigNodes that do not contain a kdb::Key
	 * @return The color of the ConfigNodes that do not contain a kdb::Key
	 */
	QColor nodeWithoutKeyColor () const;

	/**
	 * @brief setKDB Makes the current color settings permanent.
	 */
	Q_INVOKABLE void setKDB ();

	/**
	 * @brief reset Deletes all permanent keys and restores the colors to the default values.
	 */
	Q_INVOKABLE void reset ();

public slots:
	/**
	 * @brief setHighlightColor Sets the new highlight color.
	 * @param color The new highlight color.
	 */
	void setHighlightColor (const QColor & color);

	/**
	 * @brief setFrameColor Sets the new frame color.
	 * @param color The new frame color.
	 */
	void setFrameColor (const QColor & color);

	/**
	 * @brief setNodeWithKeyColor Sets the new color for ConfigNode s that contain a kdb::Key
	 * @param color The new color for ConfigNodes that contain a kdb::Key
	 */
	void setNodeWithKeyColor (const QColor & color);

	/**
	 * @brief setNodeWithoutKeyColor Sets the new color for ConfigNode s that do not contain a kdb::Key
	 * @param color The new color for ConfigNode s that do not contain a kdb::Key
	 */
	void setNodeWithoutKeyColor (const QColor & color);

signals:
	/**
	 * @brief highlightColorChanged This signal is emitted if the highlight color is changed. The view will update accordingly.
	 */
	void highlightColorChanged ();

	/**
	 * @brief frameColorChanged This signal is emitted if the frame color is changed. The view will update accordingly.
	 */
	void frameColorChanged ();

	/**
	 * @brief nodeWithKeyColorChanged This signal is emitted if the color for @link ConfigNode s that contain a @link kdb::Key
	 * is changed. The view will update accordingly.
	 */
	void nodeWithKeyColorChanged ();

	/**
	 * @brief nodeWithoutKeyColorChanged This signal is emitted if the color for @link ConfigNode s that do not contain
	 * a @link kdb::Key is changed. The view will update accordingly.
	 */
	void nodeWithoutKeyColorChanged ();

private:
	void getKDB ();

	kdb::KeySet m_config;

	QColor m_highlightColor;
	QColor m_frameColor;
	QColor m_nodeWithKeyColor;
	QColor m_nodeWithoutKeyColor;

	std::string m_profile;

	std::string m_base;
	std::string m_highlightColorString;
	std::string m_frameColorString;
	std::string m_nodeWKeyColorString;
	std::string m_nodeWOKeyColorString;

	std::string m_legacyBase;
	std::string m_legacyHighlightColorString;
	std::string m_legacyFrameColorString;
	std::string m_legacyNodeWKeyColorString;
	std::string m_legacyNodeWOKeyColorString;

	/**
	 * @brief append Updates a color. On the next @link setKDB() action this color will be permanent.
	 * @param keyName The type of the color (e.g. "highlight_color")
	 * @param color The color.
	 */
	void append (const std::string & keyName, const QColor & color);

	/**
	 * @brief lookupColor Retrieves the current color for an item.
	 * @param keyName The type of the color (e.g. "highlight_color")
	 * @param color the color to set
	 */
	void lookupColor (const std::string & keyName, QColor & color) const;
};

Q_DECLARE_METATYPE (GUISettings)

#endif // GUISETTINGS_HPP
