# HI-Parser
Short for Happy Inspector Parser, this script-like java applet takes the horribly unreadable output from Happy Inspector and puts it into a nicer, more readable format.<br/>
Happy Inspector is an app that helps perform Due Diligence on a property, usually multi-family.

## History of HI-Parser
(Because I didn't keep track of a changelog in the initial steps before release, this'll have to do.  Sorry!)

#### The Dawn of HI-Parser and the dreaded CLI
In the first version of HI-Parser (v1), I made a command line interface that had functions behind to parse and output the spreadsheet based on what I thought the functionality should be.<br/>
I would later find that what I thought the functionality should be and what my superiors thought the functionality should be were two completely separate and individual ideas.

#### HI-Parser, Episode II: Attack of the GUI
In the second iteration of HI-Parser (v2), I added a very simple GUI interface that replaced the CLI, but the basic functionality was still there.  I would add features and steps that had been discussed with the people in charge of Due Diligence.

#### HI-Parser, Episode III: Revenge of the Simplicity
The third and (hopefully) final iteration of HI-Parser (v3), has an improved (and very simple) user interface and involves functionality agreed upon by the members of Due Diligence.

## How Happy Inspector works
*(I know you're not on my Github to hear about my company's Due Diligence procedure, so let's keep this short and sweet)*

1.  We input a bunch of units/buildings into Happy Inspector.
2.  With pre-made templates, unit inspections are done on each unit.
3.  At the end of inspections, we download a Folder Export from Happy Inspector, which is that big nasty spreadsheet I was talking about in the first bit.

## How HI-Parser works

1.  The process starts by uploading that big nasty spreadsheet from Happy Inspector.  HI-Parser will then import all of the units/buildings and create objects with a bunch of attributes.  Some examples:

  * *Entry/Hall has a floor, and that floor has a material (Wood, Sheey Vinyl, Carpet, etc.)*<br/>
  * *Kitchen has countertops, and they have a material (Granite, Laminate, etc.)*<br/>
  * *Range/Oven has a type (Gas/Electric) and a brand (Kenmore, GE, Magic Chef, etc.)* <br/>
  * You get the idea.<br/><br/>

2.  Once the data is imported, you can choose to export a Consolidated Interior sheet, which will include the 1, 2 or 3 score for each item in the unit/building, the items' details (flooring material, countertop material, range type/model, etc.), the photos if there are any, as well as any notes for the items.<br/>
*(Exports to a preformatted Excel template)*

3.  You can also create a CapEx report, which just exports the 1, 2, or 3 scores for each item, which is used to determine the Capital Expenditures for the property.<br/>
*(Also exports to a preformatted Excel template)*

## Changelog
  **v1.0** - 16 February 2018
  * Initial Release, features include:
    * Import of units/building based on a specific inspection template from Happy Inspector
    * Export of unit/building data to Consolidated Interior/Exterior spreadsheet templates
    * Export of unit/building data to a CapEx Report spreadsheet template
    * Export of all of the image files to both web and local file form
  * Possible upcoming features:
    * Creation of a individual unit/building report
    * A sort of "memory" that will allow you to leave off where you started (so you don't have to keep reloading the same spreadsheet)
    * Logging, so we know what broke and how bad

&copy; Pat Ripley, Fireside Financial 2017-2018
